package br.com.training.inventory_control_web_system.infrastructure.config;

import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
import br.com.training.inventory_control_web_system.domain.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProductService productService(ProductEventPublisher eventPublisher) {
        return new ProductService(eventPublisher);
    }
}