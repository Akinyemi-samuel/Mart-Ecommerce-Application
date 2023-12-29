package com.samfrosh.controller;

import com.samfrosh.dto.request.EditUserDto;
import com.samfrosh.dto.request.PasswordChangeDto;
import com.samfrosh.dto.response.AuthenticationResponse;
import com.samfrosh.dto.response.DtoUser;
import com.samfrosh.dto.request.UserDto;
import com.samfrosh.exception.UserExits;
import com.samfrosh.service.UserService;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin()
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<AuthenticationResponse> newUser(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.newUser(userDto));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> Login(@RequestBody UserDto userDto) throws UserExits {
        return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(userDto));
    }

    @GetMapping("/userdetails")
    public ResponseEntity<DtoUser> getUserDetailsByToken(HttpServletRequest request) throws UserExits {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication == null || !authentication.isAuthenticated())) {
            throw new MalformedJwtException("User is not authenticated");
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByUserName(username));
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> UpdateUserDetails(@PathVariable(value = "id") Long id, @RequestBody EditUserDto editUserDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.UpdateUserDetails(id, editUserDto));
    }

    @PostMapping("/update/password/{id}")
    public ResponseEntity<String> UpdateUserPassword(@PathVariable(value = "id") Long id, @RequestBody PasswordChangeDto passwordChangeDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.UpdateUserPassword(id, passwordChangeDto));
    }

}
