package ir.fardup.product.category.aggregate;

import com.fardup.msutility.customexception.BusinessException;
import ir.fardup.product.category.controller.CategoryCreateModel;
import ir.fardup.product.category.orm.CategoryRepository;
import ir.fardup.product.product.controller.model.ProductCreateModel;
import ir.fardup.product.util.BusinessExceptionKeyImpl;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class CategoryAggregate {

    @AggregateIdentifier
    private String eventId;

    private Integer id;

    private String title;

    @CommandHandler
    public CategoryAggregate(CategoryCreateModel categoryCreateModel, CategoryRepository categoryRepository) {
        /*if (categoryRepository.existsByTitle(categoryCreateModel.getTitle())) {
            throw new BusinessException(BusinessExceptionKeyImpl.DUPLICATE_TITLE, categoryCreateModel.getTitle());
        }*/
        AggregateLifecycle.apply(categoryCreateModel);
    }


    @EventSourcingHandler
    public void create(CategoryCreateModel categoryCreateModel) {
        this.eventId = categoryCreateModel.getEventId();
        this.title = categoryCreateModel.getTitle();
    }
}
