package com.samfrosh.dto.response;

import com.samfrosh.model.Role;

public record DtoUser(
        Long id,

        String fullName,

        String email,

        Role role
) {
}
