package com.example.demo.problem.one;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TopologyOne {

    public static final String INPUT_TOPIC = "demo.one.input";
    public static final String OUTPUT_TOPIC = "demo.one.output";

    @Bean
    public KStream<String, String> problemOne(StreamsBuilder builder) {
        KStream<String, String> filtered = builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), Serdes.String()))
                .flatMapValues(value -> Arrays.asList(value.split("\\s+")))
                .filter((key, value) -> value.length() >= 5)
                .peek((key, value) -> System.out.println("PASS: " + value));

        filtered.to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()));
        return filtered;
    }
}
