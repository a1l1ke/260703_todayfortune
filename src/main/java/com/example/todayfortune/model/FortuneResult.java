package com.example.todayfortune.model;

// model
public record FortuneResult(
        String text,
        String timestamp
) {
    // EL Parser
    public String getText() {
        return text;
    }
    public String getTimestamp() {
        return timestamp;
    }
}
