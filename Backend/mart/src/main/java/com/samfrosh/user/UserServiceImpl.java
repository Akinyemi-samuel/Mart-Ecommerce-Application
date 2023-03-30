package com.samfrosh.user;

import com.samfrosh.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Mapper mappertoDto;


    @Override
    public DtoUser findUserByUserName(String userName) throws UserExits {
        Optional<DtoUser> optional = userRepository.findByEmail(userName).map(mappertoDto);
        return optional.orElseThrow(()-> new UserExits("User not found"));
    }

    @Override
    public AuthenticationResponse newUser(UserDto userDto) throws Exception {

        Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
        User user = null;
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
        User user = null;
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
}
