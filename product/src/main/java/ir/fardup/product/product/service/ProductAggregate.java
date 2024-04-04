package ir.fardup.product.product.service;

import com.fardup.msutility.customexception.BusinessException;
import ir.fardup.product.category.aggregate.CategoryAggregate;
import ir.fardup.product.category.aggregate.CategoryAggregateMember;
import ir.fardup.product.category.controller.CategoryCreateModel;
import ir.fardup.product.category.orm.CategoryRepository;
import ir.fardup.product.product.controller.model.ProductCreateModel;
import ir.fardup.product.product.controller.model.ProductModel;
import ir.fardup.product.product.controller.model.ProductUpdateModel;
import ir.fardup.product.product.orm.ProductRepository;
import ir.fardup.product.util.BusinessExceptionKeyImpl;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @AggregateIdentifier
    private String eventId;

    private Integer id;

    private String title;
    private BigDecimal price;
    private Integer quantity;

    @AggregateMember
    private CategoryAggregateMember categoryAggregateMember;

    @CommandHandler
    public ProductAggregate(ProductCreateModel productCreateModel,
                            ProductRepository productRepository,
                            CategoryRepository categoryRepository)throws Exception {
        //validate create product command
        if (productRepository.findByTitle(productCreateModel.getTitle()) != null) {
            throw new BusinessException(BusinessExceptionKeyImpl.DUPLICATE_TITLE, productCreateModel.getTitle());
        }
        if (categoryRepository.existsById(productCreateModel.getCategoryId())) {
            throw new BusinessException(BusinessExceptionKeyImpl.NOT_FOND, productCreateModel.getId().toString());
        }

        AggregateLifecycle.createNew(CategoryAggregate.class,() -> new CategoryAggregate(CategoryCreateModel.builder()
                .eventId(UUID.randomUUID().toString())
                .title("t")
                .build(),categoryRepository));
        AggregateLifecycle.apply(productCreateModel);

    }

    @CommandHandler
    public ProductAggregate(ProductUpdateModel productUpdateModel,
                            ProductRepository productRepository,
                            CategoryRepository categoryRepository) throws Exception {
        //validate and select needs
        AggregateLifecycle.apply(productUpdateModel);
    }

    @EventSourcingHandler
    public void create(ProductCreateModel productCreateModel) {
        this.categoryAggregateMember = new CategoryAggregateMember(UUID.randomUUID().toString(), 12, "t");
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

    private void validate(ProductModel productModel) {

    }
}
