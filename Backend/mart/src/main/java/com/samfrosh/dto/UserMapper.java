package com.samfrosh.dto;

import com.samfrosh.dto.response.DtoUser;
import com.samfrosh.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

//! used to map the value of user to dtouser for object transfer
@Service
public class UserMapper implements Function<User, DtoUser> {
    @Override
    public DtoUser apply(User user) {
        return new DtoUser(
                user.getId(),user.getFullName(), user.getEmail(), user.getRole()
        );
    }
}
