package com.example.demo.controller;

import com.example.demo.constant.KafkaConstants;
import com.example.demo.constant.UIMessages;
import com.example.demo.service.kafka.KafkaProducerService;
import com.example.demo.util.result.Result;
import com.example.demo.util.result.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/clearDatabase")
    public Result clearDatabase(@RequestParam String message) {
        kafkaProducerService.sendMessage(message, KafkaConstants.DATABASE_TOPIC);

        return new SuccessResult(UIMessages.SUCCESS);
    }

    @PostMapping("/clearCache")
    public Result clearCache(@RequestParam String message) {
        kafkaProducerService.sendMessage(message, KafkaConstants.CACHE_TOPIC);

        return new SuccessResult(UIMessages.SUCCESS);
    }
}
