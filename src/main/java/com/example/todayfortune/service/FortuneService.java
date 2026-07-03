package com.example.todayfortune.service;

import com.example.todayfortune.model.FortuneResult;

import java.time.ZonedDateTime;

// service
public class FortuneService {
    private FortuneService() {
    }

    private static final FortuneService instance = new FortuneService();

    public static FortuneService getInstance() {
        return instance;
    }

    public FortuneResult getFortune(String koreanName) {
        return new FortuneResult(
                "%s라니 정말 좋은 이름이구나".formatted(koreanName),
                ZonedDateTime.now().toString()
        );
    }
}
