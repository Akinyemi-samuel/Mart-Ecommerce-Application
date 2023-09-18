package com.samfrosh.service;

import com.samfrosh.dto.AuthenticationResponse;
import com.samfrosh.dto.DtoUser;
import com.samfrosh.dto.UserDto;
import com.samfrosh.exception.UserExits;

public interface UserService {

    DtoUser findUserByUserName(String userName) throws UserExits;

    AuthenticationResponse newUser(UserDto userDto) throws Exception;

    AuthenticationResponse loginUser(UserDto userDto) throws UserExits;
}
