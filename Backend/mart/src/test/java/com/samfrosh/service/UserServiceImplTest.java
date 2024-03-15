package com.samfrosh.service;

import com.samfrosh.config.JwtService;
import com.samfrosh.dto.UserMapper;
import com.samfrosh.dto.request.EditUserDto;
import com.samfrosh.dto.request.UserDto;
import com.samfrosh.dto.response.AuthenticationResponse;
import com.samfrosh.exception.UserExits;
import com.samfrosh.model.Role;
import com.samfrosh.model.User;
import com.samfrosh.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {


    // newUser method creates a new user with encoded password and returns a valid JWT token
    @Test
    public void test_newUser_createsUserWithEncodedPasswordAndReturnsValidJwtToken() throws Exception {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper);

        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "password");
        String encodedPassword = "password";
        User user = new User(null, "John Doe", "john.doe@example.com", "password", Role.USER);
        String jwtToken = "jwtToken";

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn(encodedPassword);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // When
        AuthenticationResponse response = userService.newUser(userDto);

        // Then
        assertEquals(jwtToken, response.getToken());
        verify(userRepository, times(1)).findByEmail(userDto.getEmail());
        verify(passwordEncoder, times(1)).encode(userDto.getPassword());
        verify(jwtService, times(1)).generateToken(user);
        verify(userRepository, times(1)).save(user);
    }

    // loginUser method returns a valid JWT token for a valid user credentials
    @Test
    public void test_loginUser_returnsValidJwtTokenForValidUserCredentials() throws UserExits {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper);

        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "password");
        String encodedPassword = "encodedPassword";
        User user = new User(1L, "John Doe", "john.doe@example.com", passwordEncoder.encode(encodedPassword), Role.USER);
        String jwtToken = "jwtToken";

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDto.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // When
        AuthenticationResponse response = userService.loginUser(userDto);

        // Then
        assertEquals(jwtToken, response.getToken());
        verify(userRepository).findByEmail(userDto.getEmail());
        verify(passwordEncoder).matches(userDto.getPassword(), user.getPassword());
        verify(jwtService).generateToken(user);
    }

    // UpdateUserDetails method updates user details and returns a success message
    @Test
    public void test_UpdateUserDetails_updatesUserDetailsAndReturnsSuccessMessage() throws UserExits {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper);

        Long userId = 1L;
        EditUserDto editUserDto = new EditUserDto("John Doe", "john.doe@example.com");
        User user = new User(userId, "Old Name", "old.email@example.com","encodedPassword", Role.USER);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        String result = userService.UpdateUserDetails(userId, editUserDto);

        // Then
        assertEquals("User has been updated successfully", result);
        assertEquals(editUserDto.getFullName(), user.getFullName());
        assertEquals(editUserDto.getEmail(), user.getEmail());
        verify(userRepository).findById(userId);
        verify(userRepository).saveAndFlush(user);
    }

    // newUser method throws UserExits exception if user already exists
    @Test
    public void test_newUser_throwsUserExitsExceptionIfUserAlreadyExists() throws Exception {
        // Given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        UserMapper userMapper = mock(UserMapper.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager, userMapper);

        UserDto userDto = new UserDto("John Doe", "john.doe@example.com", "password");
        User existingUser = new User(1L, "John Doe", "john.doe@example.com", "encodedPassword", Role.USER);

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(existingUser));

        // Then
        assertThrows(UserExits.class, () -> userService.newUser(userDto));
        verify(userRepository).findByEmail(userDto.getEmail());
    }

}