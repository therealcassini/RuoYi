package com.ruoyi;

import okhttp3.*;

import java.io.IOException;

public class B {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        String jsonBody = "{\n" +
                "  \"model\": \"deepseek-r1:7b\",\n" +
                "  \"prompt\": \"Hi\",\n" +
                "  \"max_tokens\": 200,\n" +
                "  \"temperature\": 0.7,\n" +
                "  \"stream\": false\n" +
                "}";

//        RequestBody body = RequestBody.create(mediaType, "{\n  \"messages\": [\n    {\n      \"content\": \"You are a helpful assistant\",\n      \"role\": \"system\"\n    },\n    {\n      \"content\": \"Hi\",\n      \"role\": \"user\"\n    }\n  ],\n  \"model\": \"deepseek-r1:7b\",\n  \"frequency_penalty\": 0,\n  \"max_tokens\": 2048,\n  \"presence_penalty\": 0,\n  \"response_format\": {\n    \"type\": \"text\"\n  },\n  \"stop\": null,\n  \"stream\": false,\n  \"stream_options\": null,\n  \"temperature\": 1,\n  \"top_p\": 1,\n  \"tools\": null,\n  \"tool_choice\": \"none\",\n  \"logprobs\": false,\n  \"top_logprobs\": null\n}");
        RequestBody body = RequestBody.create(mediaType, jsonBody );
        Request request = new Request.Builder()
                .url("http://localhost:11434/api/generate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }
}
