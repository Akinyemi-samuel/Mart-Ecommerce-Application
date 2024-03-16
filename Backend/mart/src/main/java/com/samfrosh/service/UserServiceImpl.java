package com.samfrosh.service;

import com.samfrosh.config.JwtService;
import com.samfrosh.dto.UserMapper;
import com.samfrosh.dto.request.EditUserDto;
import com.samfrosh.dto.request.PasswordChangeDto;
import com.samfrosh.dto.request.UserDto;
import com.samfrosh.dto.response.AuthenticationResponse;
import com.samfrosh.dto.response.DtoUser;
import com.samfrosh.exception.ApiException;
import com.samfrosh.exception.UserExits;
import com.samfrosh.model.Role;
import com.samfrosh.model.User;
import com.samfrosh.repository.UserRepository;
import com.samfrosh.validations.IsEmailValid;
import com.samfrosh.validations.IsPasswordValid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mappertoDto;
    private final IsEmailValid isEmailValid;
    private final EmailService emailService;
    private final IsPasswordValid isPasswordValid;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserMapper mappertoDto, IsEmailValid isEmailValid, EmailService emailService, IsPasswordValid isPasswordValid) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.mappertoDto = mappertoDto;
        this.isEmailValid = isEmailValid;
        this.emailService = emailService;
        this.isPasswordValid = isPasswordValid;
    }

    @Override
    public DtoUser findUserByUserName(String userName) throws UserExits {
        Optional<DtoUser> optional = userRepository.findByEmail(userName).map(mappertoDto);
        return optional.orElseThrow(() -> new UserExits("User not found"));
    }

    @Override
    public AuthenticationResponse newUser(UserDto userDto) throws Exception {

        if (!isEmailValid.test(userDto.getEmail()))
            throw new ApiException("Invalid Email Found", HttpStatus.NOT_ACCEPTABLE);

        if (!isPasswordValid.test(userDto.getPassword()))
            throw new ApiException("Password is Invalid", HttpStatus.NOT_ACCEPTABLE);

        User user = findUserByEmail(userDto.getEmail());

        if (user != null){
            throw new UserExits("user already exists");
        }

        user = User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String lastName = getLastNameFromFullName.apply(user.getFullName());

        //sends an registration successful email notification
        emailService.send(user.getEmail(), emailService.registrationSuccessfulBuildEmail(lastName));

        return AuthenticationResponse.builder()
                .response("Registration Successful")
                .build();

    }

    @Override
    public AuthenticationResponse loginUser(UserDto userDto) throws UserExits {
        User user = findUserByEmail(userDto.getEmail());
        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(),
                            userDto.getPassword()
                    )
            );
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .response(jwtToken)
                    .build();
        } else {
            throw new UserExits("Your Username or password is incorrect");
        }
    }

    @Override
    public String UpdateUserDetails(Long id, EditUserDto editUserDto) throws UserExits {

        if (!isEmailValid.test(editUserDto.getEmail()))
            throw new ApiException("Invalid Email Found", HttpStatus.NOT_ACCEPTABLE);

        User user = userRepository.findById(id).orElseThrow(() -> new UserExits("User Does not exist"));

        user.setFullName(editUserDto.getFullName());
        user.setEmail(editUserDto.getEmail());

        userRepository.saveAndFlush(user);

        return "User has been updated successfully";
    }

    @Override
    public String UpdateUserPassword(Long id, PasswordChangeDto passwordChangeDto) throws UserExits {

        if (!isPasswordValid.test(passwordChangeDto.getPassword()))
            throw new ApiException("Password is Invalid", HttpStatus.NOT_ACCEPTABLE);

        User user = userRepository.findById(id).orElseThrow(() -> new UserExits("User Does not exist"));

        user.setPassword(passwordEncoder.encode(passwordChangeDto.getPassword()));

        userRepository.saveAndFlush(user);

        return "User Password has been updated successfully";
    }

    private User findUserByEmail(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        return optional.orElse(null);
    }

    private Function<String, String> getLastNameFromFullName = fullName -> {
        String[] parts = fullName.split(" ");
        return parts.length > 1 ? parts[parts.length - 1] : "";
    };
}
