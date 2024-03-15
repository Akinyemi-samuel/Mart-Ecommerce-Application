package com.samfrosh.service;

import com.samfrosh.dto.request.NotificationDto;
import com.samfrosh.model.Notification;
import com.samfrosh.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class NotificationServiceTest {


    // Notification can be successfully added with valid input
    @Test
    public void test_notification_added_with_valid_input() {
        // Given
        NotificationDto notificationDto = new NotificationDto("user1", "Valid message", true);
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        NotificationService notificationService = new NotificationService(notificationRepository);

        // When
        String result = notificationService.addNotification(notificationDto);

        // Then
        verify(notificationRepository, times(1)).save(any(Notification.class));
        assertEquals("Notification has been successfully saved.", result);
    }

    // Notification can be successfully added with empty message
    @Test
    public void test_notification_added_with_empty_message() {
        // Given
        NotificationDto notificationDto = new NotificationDto("user1", "", true);
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        NotificationService notificationService = new NotificationService(notificationRepository);

        // When
        String result = notificationService.addNotification(notificationDto);

        // Then
        verify(notificationRepository, times(1)).save(any(Notification.class));
        assertEquals("Notification has been successfully saved.", result);
    }

    // Notification cannot be added with null input
    @Test
    public void test_notification_cannot_be_added_with_null_input() {
        // Given
        NotificationDto notificationDto = null;
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        NotificationService notificationService = new NotificationService(notificationRepository);

        // When
        String result = notificationService.addNotification(notificationDto);

        // Then
        verify(notificationRepository, never()).save(any(Notification.class));
        assertEquals("Fields are null.", result);
    }

    // Notification cannot be added with null message
    @Test
    public void test_notification_cannot_be_added_with_null_message() {
        // Given
        NotificationDto notificationDto = new NotificationDto("user1", null, true);
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        NotificationService notificationService = new NotificationService(notificationRepository);

        // When
        String result = notificationService.addNotification(notificationDto);

        // Then
        verify(notificationRepository, never()).save(any(Notification.class));
        assertEquals("Fields are null.", result);
    }

}