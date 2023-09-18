package com.samfrosh.dto;

import com.samfrosh.model.Role;

public record DtoUser(
        Long id,

        String fullName,

        String email,

        Role role
) {
}
