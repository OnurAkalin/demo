package com.example.demo.controllers;

import com.example.demo.constants.KafkaConstants;
import com.example.demo.constants.UIMessages;
import com.example.demo.services.kafka.KafkaProducerService;
import com.example.demo.utils.result.Result;
import com.example.demo.utils.result.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Result> sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message, KafkaConstants.DATABASE_TOPIC);

        return ResponseEntity.ok(new SuccessResult(UIMessages.SUCCESS));
    }

    @PostMapping("/clearCache")
    public ResponseEntity<Result> clearCache(@RequestParam String message) {
        kafkaProducerService.sendMessage(message, KafkaConstants.CACHE_TOPIC);

        return ResponseEntity.ok(new SuccessResult(UIMessages.SUCCESS));
    }
}
