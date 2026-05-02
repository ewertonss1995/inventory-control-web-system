package br.com.training.inventory_control_web_system.domain.service;

import org.springframework.stereotype.Service;

import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
import br.com.training.inventory_control_web_system.domain.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductEventPublisher eventPublisher;

    public void saveProduct(Product message) {
        log.info("[ProductService] Salvando produto: {}", message.getProductName());
        eventPublisher.publish(message);
    }
}
