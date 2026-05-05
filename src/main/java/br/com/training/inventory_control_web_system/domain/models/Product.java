package br.com.training.inventory_control_web_system.domain.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @NotBlank
    @Size(max = 50)
    private String productName;

    @NotBlank
    @Size(max = 100)
    private String productDescription;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer categoryId;
}
