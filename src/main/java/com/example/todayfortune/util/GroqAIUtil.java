package com.example.todayfortune.util;

import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GroqAIUtil implements AIUtil {
    private record GroqPayload(String model, String input) {
    }

    private record GroqResult(List<Output> output) {
        record Output(List<Content> content, String type) {
        }

        record Content(String type, String text) {
        }
    }

    @Override
    public String useAI(String prompt) {
        // 1. 환경변수 GROQ_API_KEY
        // https://console.groq.com/keys
        String GROQ_API_KEY = System.getenv("GROQ_API_KEY");
        // System.out.println("GROQ_API_KEY = " + GROQ_API_KEY);
        // 2. HttpClient - header
        // https://console.groq.com/docs/overview
        /*
        curl -X POST https://api.groq.com/openai/v1/responses \
            -H "Authorization: Bearer $GROQ_API_KEY" \
            -H "Content-Type: application/json" \
            -d '{
                "model": "openai/gpt-oss-20b",
                "input": "Explain the importance of fast language models"
            }'
         */
        String GROQ_API_URL = "https://api.groq.com/openai/v1/responses";
        ObjectMapper objectMapper = new ObjectMapper();
        GroqPayload payload = new GroqPayload("openai/gpt-oss-20b", prompt);
        // 객체로 되어 있는 데이터를 '직렬화'해서 전달하고, (ObjectMapper.writeValueAsString)
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GROQ_API_URL))
                .header("Authorization", "Bearer %s".formatted(GROQ_API_KEY))
                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(payload))
                // 3. HttpRequest - body (GroqPayload) -> Jackson
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(payload)))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
                    // 직렬화를 해서 보내고, 직렬화를 해서 받아야함
            );
            // 4. HttpResponse - Jackson -> String => Record (GroqResult)
            String body = response.body(); // 문자열 (직렬화)
//            System.out.println("body = " + body);
            GroqResult result = objectMapper.readValue(body, GroqResult.class);
//            System.out.println("result = " + result);
            // 5. return
            return result.output()
                    .stream().filter(o -> o.type().equals("message")).toList()
                    .get(0).content().get(0).text();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GroqAIUtil() {
    }

    private static final GroqAIUtil instance = new GroqAIUtil();

    public static GroqAIUtil getInstance() {
        return instance;
    }
}
