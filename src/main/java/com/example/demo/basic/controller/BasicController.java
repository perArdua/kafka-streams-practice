package com.example.demo.basic.controller;

import com.example.demo.basic.topology.BasicStreamTopology;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class BasicController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/demo/publish")
    public String publish() {
        String key = "k1";
        String value = "hello kafka streams at " + Instant.now();
        kafkaTemplate.send(BasicStreamTopology.INPUT_TOPIC, key, value);
        return "sent: key=" + key + ", value=" + value;
    }

}
