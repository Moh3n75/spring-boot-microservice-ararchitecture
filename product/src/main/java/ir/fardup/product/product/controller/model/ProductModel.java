package ir.fardup.product.product.controller.model;

import com.fardup.msutility.axon.BaseCommand;
import com.fardup.msutility.customvalidation.required.Required;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
//validation On DTO
public class ProductModel extends BaseCommand {

    private Integer id;


    @NotEmpty
    @NotNull
    private String title;

    @Required
    @PositiveOrZero
    private BigDecimal price;

    @Required
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @NotEmpty
    @Positive
    private Integer categoryId;

    public ProductModel(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
