package br.com.training.inventory_control_web_system.infrastructure.messaging.kafka.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "kafka.topics.inventory-products")
public class KafkaTopicsProperties {
        private String name;
}