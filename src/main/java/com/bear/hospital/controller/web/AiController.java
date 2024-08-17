package com.bear.hospital.controller.web;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final Generation generation;

    @Value("${ai.api.key}")
    private String appKey;

    @Autowired
    public AiController(Generation generation) {
        this.generation = generation;
    }

    @GetMapping("/send")
    public Flux<ServerSentEvent<String>> aiTalk(@RequestParam String question, HttpServletResponse response) throws NoApiKeyException, InputRequiredException {

        String distriction="你现在是一个导诊，请直接用文字回答我接下来的问题："+question;

        // 构建消息对象
        Message message = Message.builder().role(Role.USER.getValue()).content(distriction).build();

        // 构建通义千问参数对象
        GenerationParam param = GenerationParam.builder()
                .model(Generation.Models.QWEN_PLUS)
                .messages(Arrays.asList(message))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .apiKey(appKey)
                .build();

        // 执行文本生成操作，并流式返回结果
        Flowable<GenerationResult> result = generation.streamCall(param);
        System.out.println(result);
        return Flux.from(result)
                .map(m -> {
                    String content = m.getOutput().getChoices().get(0).getMessage().getContent();
                    return ServerSentEvent.<String>builder().data(content).build();
                })
                .publishOn(Schedulers.boundedElastic())
                .doOnError(e -> {
                    // 错误处理
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", "400");
                    map.put("message", "has mistake " + e.getMessage());
                    try {
                        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
                        response.setCharacterEncoding("UTF-8");
                        response.getOutputStream().print(JsonUtils.toJson(map));
                        System.out.println();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
    }
}
