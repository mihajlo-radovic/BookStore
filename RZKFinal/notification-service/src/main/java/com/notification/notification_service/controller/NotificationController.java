package com.notification.notification_service.controller;

import com.notification.notification_service.model.Notification;
import com.notification.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<Notification> getNotificationId(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }

    @PostMapping("/notifications")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.create(notification));
        //return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.create(notification));
    }
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.ok("Notification deleted");
    }

}
