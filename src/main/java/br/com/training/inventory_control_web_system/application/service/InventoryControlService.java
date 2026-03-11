package br.com.training.inventory_control_web_system.application.service;

import org.springframework.stereotype.Service;

import br.com.training.inventory_control_web_system.application.port.InventoryProductPublisher;
import br.com.training.inventory_control_web_system.domain.product.dto.ProductMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryControlService {

    private final InventoryProductPublisher productPublisher;

    public void saveProduct(ProductMessage message) {
        productPublisher.publishProduct(message);
    }
}
