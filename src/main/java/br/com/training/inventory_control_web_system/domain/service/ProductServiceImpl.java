package br.com.training.inventory_control_web_system.domain.service;

import org.springframework.stereotype.Service;

import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
import br.com.training.inventory_control_web_system.domain.port.ProductService;
import br.com.training.inventory_control_web_system.domain.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductEventPublisher eventPublisher;

    public void saveProduct(Product product) {
        log.info("[ProductService] Salvando produto: {}", product.getProductName());
        eventPublisher.publish(product);
    }
}
