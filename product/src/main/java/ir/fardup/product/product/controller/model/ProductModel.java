package ir.fardup.product.product.controller.model;

import com.fardup.msutility.customvalidation.required.Required;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
//validation On DTO
public class ProductModel /*extends BaseCommand<String>*/ {

    @TargetAggregateIdentifier
    public String eventId;

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

    public ProductModel(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
