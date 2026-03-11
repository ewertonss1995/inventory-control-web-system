package br.com.training.inventory_control_web_system.application.port;

import br.com.training.inventory_control_web_system.domain.product.dto.ProductMessage;

public interface InventoryProductPublisher {
    void publishProduct(ProductMessage message);
}