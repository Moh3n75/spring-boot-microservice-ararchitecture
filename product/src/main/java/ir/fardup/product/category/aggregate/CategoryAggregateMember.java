package ir.fardup.product.category.aggregate;

import ir.fardup.product.category.controller.CategoryCreateModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

@AllArgsConstructor
@NoArgsConstructor
public class CategoryAggregateMember {

    @EntityId
    private String eventId;

    private Integer id;

    private String title;

    @CommandHandler
    public void handle(CategoryCreateModel categoryCreateModel) {
        AggregateLifecycle.apply(categoryCreateModel);
    }


    @EventSourcingHandler
    public void on(CategoryCreateModel categoryCreateModel) {
        // 2.
        this.eventId = categoryCreateModel.getEventId();
        this.title = categoryCreateModel.getTitle();
    }

}
