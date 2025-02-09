package com.ruoyi;
import com.google.gson.Gson;
import lombok.Data;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestLocalDeepseekApi {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        // 创建请求数据
        ChatRequest requestData = createRequest();
        Gson gson = new Gson();
        String jsonRequestBody = gson.toJson(requestData);

        System.out.println("************");
        System.out.println(jsonRequestBody);
        System.out.println("************");

        // 构造请求体
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonRequestBody);

        // 创建请求
        Request request = new Request.Builder()
                .url("http://localhost:11434/api/generate")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Response: " + response.body().string());
            } else {
                System.out.println("Request failed with status: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ChatRequest createRequest() {
        ChatRequest request = new ChatRequest();
        request.setModel("deepseek-r1:7b");
        request.setStream(false);
        request.setMaxTokens(200);
        request.setTemperature(0.7);

        List<Message> messages = new ArrayList<>();
        Message systemMessage = new Message();
        systemMessage.setRole("system");
        systemMessage.setContent("You are a helpful assistant.");

        Message userMessage = new Message();
        userMessage.setRole("user");
        userMessage.setContent("你今天在干什么");

        messages.add(systemMessage);
        messages.add(userMessage);

//        request.setMessages(messages);
        request.setPrompt("你好吗");

        return request;
    }
}


/**
 * "  \"max_tokens\": 200,\n" +
 *                 "  \"temperature\": 0.7,\n" +
 */
@Data
class ChatRequest {
    private String model;
//    private List<Message> messages;
    private boolean stream;
    private int maxTokens;
    private Double temperature;
    private String prompt;

    // Getters and Setters



    // Message class

}
@Data
class Message {
    private String role;
    private String content;

}