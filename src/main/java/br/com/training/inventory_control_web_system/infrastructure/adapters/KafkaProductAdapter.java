package br.com.training.inventory_control_web_system.infrastructure.adapters;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
import br.com.training.inventory_control_web_system.domain.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProductAdapter implements ProductEventPublisher {

    private final KafkaTemplate<String, Product> kafkaTemplate;
    @Value("${app.kafka.product-topic:default-topic}")
    private String topicName;

    public KafkaProductAdapter(KafkaTemplate<String, Product> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
 
    @Override
    public void publish(Product message) {
        kafkaTemplate.send(topicName, message.getProductName(), message)
            .whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Produto {} enviado para o tópico: {}", message.getProductName(), topicName);
                } else {
                    log.error("Erro ao enviar para o tópico {}: {}", topicName, ex.getMessage());
                }
            });
    }
}
