package com.example.demo.basic.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class BasicStreamTopology {

    public static final String INPUT_TOPIC = "demo.input";
    public static final String OUTPUT_TOPIC = "demo.output";

    @Bean
    public KStream<String, String> wordCountTopology(StreamsBuilder builder) {

        KStream<String, String> lines =
                builder.stream(
                        INPUT_TOPIC,
                        Consumed.with(Serdes.String(), Serdes.String())
                );

        KTable<String, Long> wordCounts =
                lines
                        .flatMapValues(value ->
                                Arrays.asList(value.toLowerCase().trim().split("\\s+")
                                )
                        )
                        .groupBy(
                                (key, word) -> word,
                                Grouped.with(Serdes.String(), Serdes.String())
                        )
                        .count();

        wordCounts
                .toStream()
                .mapValues(Object::toString)
                .to(
                        OUTPUT_TOPIC,
                        Produced.with(Serdes.String(), Serdes.String())
                );
        return lines;
    }
}
