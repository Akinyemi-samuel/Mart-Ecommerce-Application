package com.samfrosh.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

//! used to map the value of user to dtouser for object transfer
@Service
public class Mapper implements Function<User, DtoUser> {
    @Override
    public DtoUser apply(User user) {
        return new DtoUser(
                user.getId(),user.getFullName(), user.getEmail(), user.getRole()
        );
    }
}
