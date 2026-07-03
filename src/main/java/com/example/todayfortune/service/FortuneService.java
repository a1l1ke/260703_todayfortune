package com.example.todayfortune.service;

import com.example.todayfortune.model.FortuneResult;
import com.example.todayfortune.util.AIUtil;
import com.example.todayfortune.util.GroqAIUtil;

import java.time.ZonedDateTime;

// service
public class FortuneService {
    private static final AIUtil aiUtil = GroqAIUtil.getInstance();

    private FortuneService() {
    }

    private static final FortuneService instance = new FortuneService();

    public static FortuneService getInstance() {
        return instance;
    }

    public FortuneResult getFortune(String koreanName) {
//        return new FortuneResult(
//                "%s라니 정말 좋은 이름이구나".formatted(koreanName),
//                ZonedDateTime.now().toString()
//        );
        return new FortuneResult(
//                aiUtil.useAI("%s라니 정말 좋은 이름이구나".formatted(koreanName)),
                aiUtil.useAI("%s 라는 이름을 가진 사람의 오늘(%s) 운세를 100자 이내로 알려줘".formatted(koreanName, ZonedDateTime.now().toString())),
                ZonedDateTime.now().toString()
        );
    }
}
