package br.com.training.inventory_control_web_system.domain.port;

import br.com.training.inventory_control_web_system.domain.models.Product;

public interface ProductEventPublisher {
    void publish(Product message);
}