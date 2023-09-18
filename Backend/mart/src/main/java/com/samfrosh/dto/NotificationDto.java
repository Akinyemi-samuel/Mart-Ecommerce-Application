package com.samfrosh.dto;

public record NotificationDto(
        String userId,
        String message,
        boolean status
) {
}
