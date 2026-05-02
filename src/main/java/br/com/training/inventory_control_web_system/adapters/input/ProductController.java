package br.com.training.inventory_control_web_system.infrastructure.adapters.input;

import br.com.training.inventory_control_web_system.domain.models.Product;
import br.com.training.inventory_control_web_system.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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