package com.samfrosh.service;

import com.samfrosh.dto.request.EditUserDto;
import com.samfrosh.dto.request.PasswordChangeDto;
import com.samfrosh.dto.response.AuthenticationResponse;
import com.samfrosh.dto.response.DtoUser;
import com.samfrosh.dto.request.UserDto;
import com.samfrosh.exception.UserExits;

public interface UserService {

    DtoUser findUserByUserName(String userName) throws UserExits;

    AuthenticationResponse newUser(UserDto userDto) throws Exception;

    AuthenticationResponse loginUser(UserDto userDto) throws UserExits;

    String UpdateUserDetails(Long id, EditUserDto editUserDto) throws UserExits;

    String UpdateUserPassword(Long id, PasswordChangeDto passwordChangeDto) throws UserExits;
}
