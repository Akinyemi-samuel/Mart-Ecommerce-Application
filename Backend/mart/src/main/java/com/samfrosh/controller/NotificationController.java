package com.samfrosh.controller;

import com.samfrosh.ConstantValues;
import com.samfrosh.dto.request.NotificationDto;
import com.samfrosh.dto.response.DtoNotification;
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
        return notificationService.addNotification(notificationDto);
    }

    @GetMapping
    public List<DtoNotification> getNotifications(@RequestParam("userId") String userId) {
        return notificationService.getNotificationsByIdOrGeneral(userId);
    }
}
