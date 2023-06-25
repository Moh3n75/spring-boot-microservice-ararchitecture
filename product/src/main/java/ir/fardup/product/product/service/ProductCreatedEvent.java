package ir.fardup.product.product.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreatedEvent {

    private String eventId;

    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer quantity;
}
