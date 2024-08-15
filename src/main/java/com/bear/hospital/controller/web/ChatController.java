package com.bear.hospital.controller.web;



import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("/wenxin")
public class ChatController {

    @Value("${wenxin.access-token}")
    private String apiKey;

    @Value("${wenxin.api-url}")
    private String apiUrl;

    @GetMapping("/shabi")
    public String queryWenxin(String question) {
        // 构造请求参数和头信息
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"user\": \"" + question + "\"}";
//        RequestBody body = RequestBody.create(json, JSON);
        RequestBody body = RequestBody.create(JSON, "{\"temperature\":0.95,\"top_p\":0.8,\"penalty_score\":1,\"disable_search\":false,\"enable_citation\":false}");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=" + apiKey)
                .addHeader("Content-Type", "application/json")
                .method("POST", body)
//                .addHeader("Authorization", "Bearer " + apiKey)
//                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBody = response.body().string();
            // 解析响应结果，返回用户需要的部分
            System.out.println(responseBody);
//            return parseResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

