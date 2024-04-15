package ir.fardup.order.order.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateModel extends OrderModel {
    private String OrderSagaId;
    private BigDecimal totalAmount;
    private Integer productId;
    private Integer quantity;
}
