package br.com.training.inventory_control_web_system.infrastructure.messaging.kafka.config;

import java.util.HashMap;
import java.util.Map;

import br.com.training.inventory_control_web_system.infrastructure.messaging.kafka.properties.KafkaTopicsProperties;
import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableConfigurationProperties(KafkaTopicsProperties.class)
@RequiredArgsConstructor
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.client-id}")
    private String clientId;

    @Value("${kafka.acks}")
    private String acks;

    @Value("${kafka.retries}")
    private Integer retries;

    @Value("${kafka.batch-size}")
    private Integer batchSize;

    @Value("${kafka.linger-ms}")
    private Integer lingerMs;

    @Value("${kafka.buffer-memory}")
    private Long bufferMemory;

    @Value("${kafka.compression-type}")
    private String compressionType;

    private final ObjectMapper objectMapper;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {

        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        config.put(ProducerConfig.ACKS_CONFIG, acks);
        config.put(ProducerConfig.RETRIES_CONFIG, retries);
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        config.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        config.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);

        // Configura o JsonSerializer com ObjectMapper injetado
        JsonSerializer<Object> jsonSerializer = new JsonSerializer<>(objectMapper);
        jsonSerializer.setAddTypeInfo(false); // evita headers extras

        return new DefaultKafkaProducerFactory<>(
                config,
                new StringSerializer(),
                jsonSerializer);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
