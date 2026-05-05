# inventory-control-web-system

readme_content = """# Inventory Control Web System - Product Flow

This documentation describes the technical flow for product creation and event notification, following the **Hexagonal Architecture** (Ports and Adapters) pattern.

## 1. Architectural Overview
The system is designed to keep the core business logic isolated from external infrastructures. It uses **Spring Boot** for orchestration and **Apache Kafka** as the message broker.

## 2. Technical Flow Description

### A. Inbound Adapter (Entry Point)
* **Component:** `ProductController`
* **Protocol:** REST (HTTP POST)
* **Route:** `/api/products`
* **Process:**
    1. Receives the `Product` JSON payload.
    2. Logs the operation: `[ProductController] Creating product: {name}`.
    3. Calls the `ProductService` port.
    4. Returns `201 Created` status.

### B. Domain Service (Core Logic)
* **Component:** `ProductServiceImpl`
* **Responsibility:** Orchestrates the business use case.
* **Process:**
    1. Receives the domain model from the controller.
    2. Logs the domain action: `[ProductService] Saving product: {name}`.
    3. Triggers the outbound port `ProductEventPublisher`.
    *Note: The domain layer remains agnostic to the messaging technology used (Kafka).*

### C. Outbound Adapter (Infrastructure)
* **Component:** `KafkaProductAdapter`
* **Technology:** Spring Kafka (`KafkaTemplate`)
* **Process:**
    1. Sends the message to the topic defined in the property `app.kafka.product-topic`.
    2. Executes an asynchronous operation using `whenComplete`.
    3. Logs the result:
        * **Success:** "Product {name} sent to topic: {topic}".
        * **Failure:** Error message detailing the exception.

## 3. Dependency Injection Configuration
The system uses a manual configuration class (`BeanConfig`) to instantiate the service. This ensures that the domain service remains a clean Java class without infrastructure-specific annotations (like `@Service`).

```java
@Bean
public ProductServiceImpl productService(ProductEventPublisher eventPublisher) {
    return new ProductServiceImpl(eventPublisher);
}

```

## 4. Image of the message publishing flow

![Message publishing flow](/assets/flow_inventory_control_system.png)