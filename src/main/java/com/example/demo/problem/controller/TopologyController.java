package com.example.demo.problem.controller;

import com.example.demo.problem.one.TopologyOne;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TopologyController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/one/publish")
    public String publish() {
        String key = "k1";
        String value = "a ab abc abcd abcde abcdef";
        kafkaTemplate.send(TopologyOne.INPUT_TOPIC, key, value);
        return "sent: key=" + key + ", value=" + value;
    }
}
