package com.fardup.order.order.controller.model;

import com.fardup.msutility.axon.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class OrderCreateModel extends OrderModel {
    private Integer id;
    private BigDecimal totalAmount;
}
