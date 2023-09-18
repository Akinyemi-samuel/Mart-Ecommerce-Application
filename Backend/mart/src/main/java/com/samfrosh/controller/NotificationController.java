package com.samfrosh.controller;

import com.samfrosh.ConstantValues;
import com.samfrosh.dto.NotificationDto;
import com.samfrosh.model.Notification;
import com.samfrosh.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(ConstantValues.DEFAULT_URL+"/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addNotification(@RequestBody NotificationDto notificationDto){
        String notificationResponse = notificationService.addNotification(notificationDto);
        return notificationResponse;
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getNotifications();
    }
}
