package br.com.training.inventory_control_web_system.infrastructure.config;

import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
import br.com.training.inventory_control_web_system.domain.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProductServiceImpl productService(ProductEventPublisher eventPublisher) {
        return new ProductServiceImpl(eventPublisher);
    }
}