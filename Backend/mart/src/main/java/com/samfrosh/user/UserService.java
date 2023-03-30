package com.samfrosh.user;

public interface UserService {

    DtoUser findUserByUserName(String userName) throws UserExits;

    AuthenticationResponse newUser(UserDto userDto) throws Exception;

    AuthenticationResponse loginUser(UserDto userDto) throws UserExits;
}
