package ir.fardup.models.product.command;

import com.fardup.msutility.axon.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductReserveModel extends BaseCommand {

    private String orderSagaId;

    private Integer productId;

    private Integer quantity;
}
