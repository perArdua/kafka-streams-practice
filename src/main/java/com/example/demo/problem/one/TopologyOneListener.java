package com.example.demo.problem.one;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TopologyOneListener {

    @KafkaListener(topics = TopologyOne.OUTPUT_TOPIC, groupId = "demo-one-output-listener")
    public void listen(String message) {
        System.out.println("OUTPUT: " + message);
    }
}
