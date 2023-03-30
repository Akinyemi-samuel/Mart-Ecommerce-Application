package com.samfrosh.user;

import com.samfrosh.user.Role;

public record DtoUser(
        Long id,

        String fullName,

        String email,

        Role role
) {
}
