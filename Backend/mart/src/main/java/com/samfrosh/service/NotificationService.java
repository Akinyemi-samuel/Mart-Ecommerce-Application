package com.samfrosh.service;

import com.samfrosh.dto.NotificationDto;
import com.samfrosh.model.Notification;
import com.samfrosh.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public String addNotification(NotificationDto notificationDto){

        Notification notification = Notification.builder()
                .datePosted(LocalDate.now())
                .message(notificationDto.message())
                .userId(notificationDto.userId())
                .status(notificationDto.status())
                .build();

        notificationRepository.save(notification);
        return "Notification has been successfully saved.";
    }


    public List<Notification> getNotifications() {
       return notificationRepository.findAll();
    }

//    public List<Notification> getNotificationsById(String userId){
//
//    }
}
