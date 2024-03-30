package ir.fardup.product.product.service;

import ir.fardup.product.product.controller.model.ProductCreateModel;
import ir.fardup.product.product.controller.model.ProductModel;
import ir.fardup.product.product.controller.model.ProductUpdateModel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @AggregateIdentifier
    private String eventId;

    private Integer id;

    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(ProductCreateModel productCreateModel) {
        //validate create product command
        AggregateLifecycle.apply(productCreateModel);

    }

    @CommandHandler
    public ProductAggregate(ProductUpdateModel productUpdateModel) {
        //validate and select needs
        AggregateLifecycle.apply(productUpdateModel);
    }

    @EventSourcingHandler
    public void create(ProductCreateModel productCreateModel) {
        this.eventId = productCreateModel.getEventId();
        this.title = productCreateModel.getTitle();
        this.price = productCreateModel.getPrice();
        this.quantity = productCreateModel.getQuantity();
    }

    @EventSourcingHandler
    public void update(ProductUpdateModel productUpdateModel) {
        this.eventId = productUpdateModel.getEventId();
        this.id = productUpdateModel.getId();
        this.title = productUpdateModel.getTitle();
        this.price = productUpdateModel.getPrice();
        this.quantity = productUpdateModel.getQuantity();
    }

    private void validate(ProductModel productModel){

    }
}
