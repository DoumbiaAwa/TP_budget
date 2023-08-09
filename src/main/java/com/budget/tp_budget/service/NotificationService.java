package com.budget.tp_budget.service;

import com.budget.tp_budget.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {

    private List<Notification> notifications = new ArrayList<>();

    public void createNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getAllNotifications() {
        return notifications;
    }


}