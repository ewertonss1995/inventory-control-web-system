package br.com.training.inventory_control_web_system.domain.service;

import org.springframework.stereotype.Service;

import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
import br.com.training.inventory_control_web_system.domain.models.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {

    private final ProductEventPublisher eventPublisher;

    public void saveProduct(Product message) {
        eventPublisher.publish(message);
    }
}
