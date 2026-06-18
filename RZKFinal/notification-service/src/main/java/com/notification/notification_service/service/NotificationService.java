package com.notification.notification_service.service;

import com.notification.notification_service.exception.NotificationNotFoundException;
import com.notification.notification_service.model.Notification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final Map<Long, Notification> notifications = new HashMap<>();
    private long counter = 1;

    public List<Notification> getAll() {
        return new ArrayList<>(notifications.values());
    }

    public Notification getById(Long id) {
        Notification n = notifications.get(id);
        if (n == null) throw new NotificationNotFoundException(id);
        return n;
    }

    public Notification create(Notification n) {
        long id = counter++;
        n.setId(id);
        notifications.put(id, n);
        System.out.println("Sent notification to: " + n.getName() + ". " + n.getMessage());
        return n;
    }

    public void delete(Long id) {
        if (!notifications.containsKey(id)) throw new NotificationNotFoundException(id);
        notifications.remove(id);
    }
}