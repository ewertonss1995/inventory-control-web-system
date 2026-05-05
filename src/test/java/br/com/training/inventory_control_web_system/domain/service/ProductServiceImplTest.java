package br.com.training.inventory_control_web_system.domain.service;

import br.com.training.inventory_control_web_system.mocks.ProductMock;
import br.com.training.inventory_control_web_system.domain.models.Product;
import br.com.training.inventory_control_web_system.domain.port.ProductEventPublisher;
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
class ProductServiceImplTest {

    @Mock
    private ProductEventPublisher eventPublisher;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Should successfully publish product event when saving product")
    void shouldPublishProductEventWhenSavingProduct() {
        // Given
        Product mockProduct = ProductMock.createValidProduct();
        
        doNothing().when(eventPublisher).publish(mockProduct);

        // When
        productService.saveProduct(mockProduct);

        // Then
        verify(eventPublisher, times(1)).publish(mockProduct);
    }

    @Test
    @DisplayName("Should ensure event publisher receives exact product data")
    void shouldPassExactDataToEventPublisher() {
        // Given
        Product mockProduct = ProductMock.createValidProduct();

        // When
        productService.saveProduct(mockProduct);

        // Then
        verify(eventPublisher).publish(mockProduct);
    }
}
