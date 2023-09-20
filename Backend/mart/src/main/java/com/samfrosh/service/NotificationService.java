package com.samfrosh.service;

import com.samfrosh.dto.request.NotificationDto;
import com.samfrosh.dto.response.DtoNotification;
import com.samfrosh.model.Notification;
import com.samfrosh.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


    public List<DtoNotification> getNotificationsByIdOrGeneral(String id) {

              return notificationRepository.findByIdAndByAll(id)
                       .orElseThrow(()-> new RuntimeException("Notification Empty"))
                       .stream().map(n -> DtoNotification.builder()
                       .userId(n.getUserId())
                       .datePosted(n.getDatePosted())
                       .message(n.getMessage())
                       .status(n.isStatus())
                       .build()).collect(Collectors.toList());

    }

}
