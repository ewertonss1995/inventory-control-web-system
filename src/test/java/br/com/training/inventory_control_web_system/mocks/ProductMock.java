package br.com.training.inventory_control_web_system.mocks;

import java.math.BigDecimal;
import br.com.training.inventory_control_web_system.domain.models.Product;

public class ProductMock {

    public static Product createValidProduct() {
        Product product = new Product();
        product.setProductName("Laptop Gamer");
        product.setProductDescription("Laptop de alta performance para jogos");
        product.setUnitPrice(BigDecimal.valueOf(3500.00));
        product.setQuantity(10);
        product.setCategoryId(2);
        return product;
    }
}