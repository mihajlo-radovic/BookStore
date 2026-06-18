package com.notification.notification_service.exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(Long id){
        super("Notification with id " + id + " not found");
    }
}
