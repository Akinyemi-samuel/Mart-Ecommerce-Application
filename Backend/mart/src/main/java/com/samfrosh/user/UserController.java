package com.samfrosh.user;

import io.jsonwebtoken.MalformedJwtException;
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
        if ((authentication == null || !authentication.isAuthenticated())){
            throw new MalformedJwtException("User is not authenticated");
        }else{
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByUserName(username));
        }
    }

}
