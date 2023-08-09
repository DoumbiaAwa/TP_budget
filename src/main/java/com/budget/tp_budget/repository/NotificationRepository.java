package com.budget.tp_budget.repository;

import com.budget.tp_budget.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}