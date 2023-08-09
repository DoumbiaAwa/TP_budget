package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.Notification;
import com.budget.tp_budget.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notification;
    @GetMapping("/get")
    public Notification getNotification() {
        Notification notification = new Notification();
        notification.setType("alert");
        notification.setMessage("Attention budget presque atteint!");
        notification.setDate("2023-08-07 15:00:00");
        return notification;
    }
}