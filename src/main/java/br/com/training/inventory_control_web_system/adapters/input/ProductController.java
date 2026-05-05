package br.com.training.inventory_control_web_system.infrastructure.adapters.input;

import br.com.training.inventory_control_web_system.domain.models.Product;
import br.com.training.inventory_control_web_system.domain.port.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Product product) {
        log.info("[ProductController] Criando produto: {}", product.getProductName());
        productService.saveProduct(product);
    }

}