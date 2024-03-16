package com.samfrosh.service;

import com.samfrosh.config.JwtService;
import com.samfrosh.dto.UserMapper;
import com.samfrosh.dto.request.EditUserDto;
import com.samfrosh.dto.request.UserDto;
import com.samfrosh.dto.response.AuthenticationResponse;
import com.samfrosh.exception.ApiException;
import com.samfrosh.exception.UserExits;
import com.samfrosh.model.Role;
import com.samfrosh.model.User;
import com.samfrosh.repository.UserRepository;
import com.samfrosh.validations.IsEmailValid;
import com.samfrosh.validations.IsPasswordValid;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {


    // creates a new user with valid email and password, and returns an authentication response
    @Test
    public void test_create_new_user_with_valid_email_and_password() throws Exception {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);
        IsEmailValid isEmailValid = mock(IsEmailValid.class);
        EmailService emailService = mock(EmailService.class);
        IsPasswordValid isPasswordValid = mock(IsPasswordValid.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper, isEmailValid, emailService, isPasswordValid);

        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "password123");

        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);

        when(isEmailValid.test(userDto.getEmail())).thenReturn(true);
        when(isPasswordValid.test(userDto.getPassword())).thenReturn(true);
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        AuthenticationResponse response = userService.newUser(userDto);

        // Then
        verify(userRepository,times(1)).save(user);
        assertEquals("Registration Successful", response.getResponse());
    }

    // logs in a user with valid email and password, and returns an authentication response
    @Test
    public void test_login_user_with_valid_email_and_password() throws UserExits {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);
        IsEmailValid isEmailValid = mock(IsEmailValid.class);
        EmailService emailService = mock(EmailService.class);
        IsPasswordValid isPasswordValid = mock(IsPasswordValid.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper, isEmailValid, emailService, isPasswordValid);

        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "password123");

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDto.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        // When
        AuthenticationResponse response = userService.loginUser(userDto);

        // Then
        assertEquals("jwtToken", response.getResponse());
    }

    // updates user details with valid email, and returns a success message
    @Test
    public void test_update_user_details_with_valid_email() throws UserExits {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);
        IsEmailValid isEmailValid = mock(IsEmailValid.class);
        EmailService emailService = mock(EmailService.class);
        IsPasswordValid isPasswordValid = mock(IsPasswordValid.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper, isEmailValid, emailService, isPasswordValid);

        Long userId = 1L;
        EditUserDto editUserDto = new EditUserDto("John Doe", "john.doe@example.com");

        User user = new User();
        user.setId(userId);

        when(isEmailValid.test(editUserDto.getEmail())).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.saveAndFlush(user)).thenReturn(user);

        // When
        String response = userService.UpdateUserDetails(userId, editUserDto);

        // Then
        assertEquals("User has been updated successfully", response);
    }

    // throws an ApiException with status code 406 if email is invalid
    @Test
    public void test_throw_api_exception_if_email_is_invalid() {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);
        IsEmailValid isEmailValid = mock(IsEmailValid.class);
        EmailService emailService = mock(EmailService.class);
        IsPasswordValid isPasswordValid = mock(IsPasswordValid.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper, isEmailValid, emailService, isPasswordValid);

        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "password123");

        when(isEmailValid.test(userDto.getEmail())).thenReturn(false);

        // When
        ApiException exception = assertThrows(ApiException.class, () -> userService.newUser(userDto));

        // Then
        assertEquals("Invalid Email Found", exception.getMessage());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getHttpStatus());
    }

    // throws a UserExits exception if user already exists
    @Test
    public void test_throw_user_exists_exception_if_user_already_exists() {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);
        IsEmailValid isEmailValid = mock(IsEmailValid.class);
        EmailService emailService = mock(EmailService.class);
        IsPasswordValid isPasswordValid = mock(IsPasswordValid.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper, isEmailValid, emailService, isPasswordValid);

        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "password123");

        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);

        when(isEmailValid.test(userDto.getEmail())).thenReturn(true);
        when(isPasswordValid.test(userDto.getPassword())).thenReturn(true);
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));

        // When
        UserExits exception = assertThrows(UserExits.class, () -> userService.newUser(userDto));

        // Then
        assertEquals("user already exists", exception.getMessage());
    }

}