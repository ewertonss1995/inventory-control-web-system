package br.com.training.inventory_control_web_system.infrastructure.messaging.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.training.inventory_control_web_system.application.port.InventoryProductPublisher;
import br.com.training.inventory_control_web_system.domain.product.dto.ProductMessage;
import br.com.training.inventory_control_web_system.infrastructure.messaging.kafka.properties.KafkaTopicsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProductProducer implements InventoryProductPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopicsProperties kafkaTopicsProperties;

    @Override
    public void publishProduct(ProductMessage message) {
        String topic = kafkaTopicsProperties.getName();
        String key = message.getProductName();

        kafkaTemplate.send(topic, key, message);
                // .whenComplete((result, ex) -> {
                //     if (ex != null) {
                //         log.error("Erro ao enviar InventoryProductEvent para Kafka", ex);
                //         return;
                //     }

                //     log.info("Evento enviado topic={} key={} partition={} offset={}",
                //             kafkaTopicsProperties,
                //             key,
                //             result.getRecordMetadata().partition(),
                //             result.getRecordMetadata().offset()
                //     );
                // })
                //;
    }
    
}
