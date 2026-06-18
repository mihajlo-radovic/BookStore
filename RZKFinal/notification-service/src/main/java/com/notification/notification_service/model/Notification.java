package com.notification.notification_service.model;

import jakarta.validation.constraints.NotBlank;

public class Notification {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Message cannot be empty")
    private String message;

    public Notification() {
    }

    public Notification(Long id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getMessage () {
        return message;
    }
    public void setMessage (String message) {
        this.message = message;
    }
}
