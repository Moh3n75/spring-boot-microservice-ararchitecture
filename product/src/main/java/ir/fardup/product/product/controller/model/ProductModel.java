package ir.fardup.product.product.controller.model;

import com.fardup.msutility.axon.BaseCommand;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductModel /*extends BaseCommand<String>*/ {

    @TargetAggregateIdentifier
    public String eventId;

    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer quantity;

    public ProductModel(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
