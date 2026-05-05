package br.com.training.inventory_control_web_system.infrastructure.adapters;

import br.com.training.inventory_control_web_system.mocks.ProductMock;
import br.com.training.inventory_control_web_system.domain.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaProductAdapterTest {

    @Mock
    private KafkaTemplate<String, Product> kafkaTemplate;

    @InjectMocks
    private KafkaProductAdapter kafkaProductAdapter;

    private final String TOPIC_NAME = "test-product-topic";

    @BeforeEach
    void setUp() {
        // Since @Value is not processed in unit tests without Spring context, 
        // we manually set the field value using ReflectionTestUtils.
        ReflectionTestUtils.setField(kafkaProductAdapter, "topicName", TOPIC_NAME);
    }

    @Test
    @DisplayName("Should successfully send message to Kafka topic")
    void shouldSendMessageToKafkaTopicSuccessfully() {
        // Given
        Product mockProduct = ProductMock.createValidProduct();
        CompletableFuture<SendResult<String, Product>> future = 
            CompletableFuture.completedFuture(new SendResult<>(null, null));

        when(kafkaTemplate.send(eq(TOPIC_NAME), eq(mockProduct.getProductName()), eq(mockProduct)))
            .thenReturn(future);

        // When
        kafkaProductAdapter.publish(mockProduct);

        // Then
        verify(kafkaTemplate).send(TOPIC_NAME, mockProduct.getProductName(), mockProduct);
    }

    @Test
    @DisplayName("Should handle Kafka failure when publishing message")
    void shouldHandleKafkaFailureWhenPublishing() {
        // Given
        Product mockProduct = ProductMock.createValidProduct();
        CompletableFuture<SendResult<String, Product>> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("Kafka connection failed"));

        when(kafkaTemplate.send(any(), any(), any())).thenReturn(future);

        // When
        kafkaProductAdapter.publish(mockProduct);

        // Then
        verify(kafkaTemplate).send(TOPIC_NAME, mockProduct.getProductName(), mockProduct);
    }
}
