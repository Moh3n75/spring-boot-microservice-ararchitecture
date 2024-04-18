package ir.fardup.product.product.controller.model;

import ir.fardup.models.product.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class ProductUpdateModel extends ProductModel {
}
