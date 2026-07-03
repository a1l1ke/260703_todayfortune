package com.example.todayfortune.util;

public class GroqAIUtil implements AIUtil {
    @Override
    public String useAI(String prompt) {
        return "";
    }

    private GroqAIUtil() {
    }

    private static final GroqAIUtil instance = new GroqAIUtil();

    public static GroqAIUtil getInstance() {
        return instance;
    }
}
