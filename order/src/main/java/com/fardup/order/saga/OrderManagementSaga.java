package com.fardup.order.saga;

import com.fardup.order.order.controller.model.OrderCreateModel;
import ir.fardup.models.product.ProductReserveModel;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "eventId")
    public void handle(OrderCreateModel orderCreateModel) {
        log.info("order create run first {}", orderCreateModel.getEventId());

        //send the commands
        commandGateway.send(ProductReserveModel.builder().productId(1).orderId(1).build(),
                (CommandCallback<ProductReserveModel, Object>) (commandMessage, commandResultMessage) -> {
                    if (commandResultMessage.isExceptional()) {

                    }
                });
    }

    @SagaEventHandler(associationProperty = "eventId")
    public void handle(ProductReserveModel productReserveModel) {
        log.info("product reserve model after call order {}", productReserveModel.getEventId());
    }

}