package com.samfrosh.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.samfrosh.config.JwtService;
import com.samfrosh.dto.request.EditUserDto;
import com.samfrosh.dto.request.PasswordChangeDto;
import com.samfrosh.dto.response.AuthenticationResponse;
import com.samfrosh.dto.response.DtoUser;
import com.samfrosh.dto.request.UserDto;
import com.samfrosh.dto.UserMapper;
import com.samfrosh.exception.UserExits;
import com.samfrosh.model.Role;
import com.samfrosh.model.User;
import com.samfrosh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mappertoDto;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserMapper mappertoDto) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.mappertoDto = mappertoDto;
    }

    @Override
    public DtoUser findUserByUserName(String userName) throws UserExits {
        Optional<DtoUser> optional = userRepository.findByEmail(userName).map(mappertoDto);
        return optional.orElseThrow(()-> new UserExits("User not found"));
    }

    @Override
    public AuthenticationResponse newUser(UserDto userDto) throws Exception {

        Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
        User user;
        if (optional.isPresent()) {
            throw new UserExits("user already exists");
        }else{
             user = User.builder().
                    fullName(userDto.getFullName()).
                    email(userDto.getEmail()).
                    password(passwordEncoder.encode( userDto.getPassword())).
                    role(Role.USER)
                    .build();
            userRepository.save(user);

        }
        var jwtToekn = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToekn)
                .build();
    }

    @Override
    public AuthenticationResponse loginUser(UserDto userDto) throws UserExits {

        Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
        User user;
        if (optional.isPresent()) {
            user = optional.get();
            if (passwordEncoder.matches(userDto.getPassword(),user.getPassword())){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userDto.getEmail(),
                                userDto.getPassword()
                        )
                );
                var jwtToekn = jwtService.generateToken(user);
                return AuthenticationResponse
                        .builder()
                        .token(jwtToekn)
                        .build();
            }else{
                throw new UserExits("Your Username or password is incorrect");
            }

        }else{
            throw new UserExits("Your Username or password is incorrect");
        }

    }

    @Override
    public String UpdateUserDetails(Long id, EditUserDto editUserDto) throws UserExits {

        User user = userRepository.findById(id).orElseThrow(()-> new UserExits("User Does not exist"));

        user.setFullName(editUserDto.getFullName());
        user.setEmail(editUserDto.getEmail());

        userRepository.save(user);

        return "User has been updated successfully";
    }

    @Override
    public String UpdateUserPassword(Long id, PasswordChangeDto passwordChangeDto) throws UserExits {
        User user = userRepository.findById(id).orElseThrow(()-> new UserExits("User Does not exist"));

        user.setPassword(passwordEncoder.encode(passwordChangeDto.getPassword()));

        userRepository.save(user);

        return "User Password has been updated successfully";
    }

}
