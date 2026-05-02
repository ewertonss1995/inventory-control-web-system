package br.com.training.inventory_control_web_system.infrastructure.adapters.input;

import br.com.training.inventory_control_web_system.domain.modelos.Product;
import br.com.training.inventory_control_web_system.domain.servicos.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Product product) {
        log.info("[ProductController] Criando produto: {}", product.getProductName());
        productService.saveProduct(product);
    }

}