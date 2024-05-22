package ir.fardup.models.product.model;

import com.fardup.msutility.axon.BaseCommand;
import com.fardup.msutility.customvalidation.required.Required;
import ir.fardup.models.payment.OrderPaymentModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

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
    @Positive
    private Integer categoryId;

    //private List<OrderPaymentModel> orderPaymentModelList;

    public ProductModel(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
