package br.com.training.inventory_control_web_system.adapters.input;

import br.com.training.inventory_control_web_system.domain.models.Product;
import br.com.training.inventory_control_web_system.domain.port.ProductService;
import br.com.training.inventory_control_web_system.infrastructure.adapters.input.ProductController;
import br.com.training.inventory_control_web_system.mocks.ProductMock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    @DisplayName("Should successfully call product service when creating a product")
    void shouldCallSaveProductWhenCreatingProduct() {
        // Given
        Product mockProduct = ProductMock.createValidProduct();
         
        doNothing().when(productService).saveProduct(mockProduct);

        // When
        productController.create(mockProduct);

        // Then
        verify(productService, times(1)).saveProduct(mockProduct);
    }

    @Test
    @DisplayName("Should ensure product service is called with exact data")
    void shouldPassExactProductDataToService() {
        // Given
        Product mockProduct = ProductMock.createValidProduct();

        // When
        productController.create(mockProduct);

        // Then
        verify(productService).saveProduct(mockProduct);
    }
}
