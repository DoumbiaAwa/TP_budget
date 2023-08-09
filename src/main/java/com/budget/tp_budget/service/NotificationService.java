package com.budget.tp_budget.service;

import com.budget.tp_budget.entity.Notification;
import com.budget.tp_budget.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notification;
    private List<Notification> notifications = new ArrayList<>();

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void createNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getAllNotifications() {
        return notifications;
    }

    private JavaMailSender javaMailSender;
    public void EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
    public void saveNotification(Notification notification) {
        if (notification != null) {
            this.notification.save(notification);
        }
    }
}