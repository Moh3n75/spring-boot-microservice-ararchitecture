package ir.fardup.product.product.controller;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel {

    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer quantity;
}
