package ir.fardup.product.category.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CategoryCreateModel extends CategoryModel{
}
