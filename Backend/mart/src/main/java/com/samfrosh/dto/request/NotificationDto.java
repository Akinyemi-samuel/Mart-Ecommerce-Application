package com.samfrosh.dto.request;

public record NotificationDto(
        String userId,
        String message,
        boolean status
) {
}
