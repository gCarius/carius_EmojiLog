package com.example.carius_emotilog;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Mood {
    // Store the mood name/type
    private String name;
    private LocalDateTime timestamp;
    // Constructor
    public Mood(String name) {
        this.name = name;
        this.timestamp = LocalDateTime.now();
    }
    public String getText() {
        return name;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // only time
        return name + " @ " + timestamp.format(formatter);
    }
}


