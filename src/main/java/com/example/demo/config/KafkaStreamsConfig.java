package com.example.demo.config;

import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.streams.KafkaStreamsInteractiveQueryService;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfiguration(KafkaProperties kafkaProperties) {
        return new KafkaStreamsConfiguration(kafkaProperties.buildStreamsProperties());
    }

    @Bean
    public KafkaStreamsInteractiveQueryService interactiveQueryService(
            StreamsBuilderFactoryBean streamsBuilderFactoryBean
    ) {
        return new KafkaStreamsInteractiveQueryService(streamsBuilderFactoryBean);
    }
}
