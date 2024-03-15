package com.samfrosh.service;

import com.samfrosh.dto.request.NotificationDto;
import com.samfrosh.dto.response.DtoNotification;
import com.samfrosh.model.Notification;
import com.samfrosh.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public String addNotification(NotificationDto notificationDto) {

        if (notificationDto != null && notificationDto.message() != null) {
            Notification notification = Notification.builder()
                    .datePosted(LocalDate.now())
                    .message(notificationDto.message())
                    .userId(notificationDto.userId())
                    .status(notificationDto.status())
                    .build();

            notificationRepository.save(notification);
            return "Notification has been successfully saved.";
        } else {
            return "Fields are null.";
        }
    }

    public List<DtoNotification> getNotificationsByIdOrGeneral(String id) {
        return notificationRepository.findByIdAndByAll(id)
                .orElse(Collections.emptyList())
                .stream()
                .map(n -> DtoNotification.builder()
                        .userId(n.getUserId())
                        .datePosted(n.getDatePosted())
                        .message(n.getMessage())
                        .status(n.isStatus())
                        .build())
                .collect(Collectors.toList());
    }

}
