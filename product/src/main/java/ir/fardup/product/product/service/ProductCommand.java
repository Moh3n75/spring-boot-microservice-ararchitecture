package ir.fardup.product.product.service;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class ProductCommand {

    @TargetAggregateIdentifier
    private final String eventId;

    private final String title;

    private final BigDecimal price;

    private final Integer quantity;
}
