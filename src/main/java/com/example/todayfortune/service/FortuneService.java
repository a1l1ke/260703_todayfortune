package com.example.todayfortune.service;

// service
public class FortuneService {
    private FortuneService() {
    }

    private static final FortuneService instance = new FortuneService();

    public static FortuneService getInstance() {
        return instance;
    }
}
