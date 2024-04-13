package com.fardup.order.order.service;

import com.fardup.order.order.controller.model.OrderCreateModel;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.List;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String eventId;
    private Integer id;
    private List<Integer> products;
    private BigDecimal totalAmount;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(OrderCreateModel orderCreateModel){
        AggregateLifecycle.apply(orderCreateModel);
    }

    @EventSourcingHandler
    public void on(OrderCreateModel orderCreateModel){
        this.eventId=orderCreateModel.getEventId();
        this.totalAmount=orderCreateModel.getTotalAmount();
    }
}
