package ir.fardup.product.product.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer quantity;

    public ProductModel(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
