package br.com.training.inventory_control_web_system.infrastructure.adapters;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
import br.com.training.inventory_control_web_system.domain.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProductAdapter implements ProductEventPublisher {

    private final KafkaTemplate<String, Product> kafkaTemplate;
    private static final String TOPIC = "product-updates";
 
    @Override
    public void publish(Product message) {
        kafkaTemplate.send(TOPIC, message.getProductName(), message)
            .whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Produto enviado com sucesso! Partição: {}", result.getRecordMetadata().partition());
                } else {
                    log.error("Falha ao enviar produto: {}", ex.getMessage());
                }
            });
    }
}
