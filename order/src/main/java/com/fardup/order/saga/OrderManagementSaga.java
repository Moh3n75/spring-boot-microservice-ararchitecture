package com.fardup.order.saga;

import com.fardup.order.order.controller.model.OrderCreateModel;
import ir.fardup.models.product.ProductReserveModel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderSagaId")
    public void handle(OrderCreateModel orderCreateModel, @MetaDataValue("processUUID") String processUUID) {
        log.info("order create run first {}", orderCreateModel.getEventId());

        //send the commands
        commandGateway.send(ProductReserveModel.builder().eventId(UUID.randomUUID().toString()).quantity(1).productId(10).orderSagaId(processUUID).build(),
                (CommandCallback<ProductReserveModel, Object>) (commandMessage, commandResultMessage) -> {
                    if (commandResultMessage.isExceptional()) {
                        commandResultMessage.exceptionResult().printStackTrace();
                    }
                });
    }

    @SagaEventHandler(associationProperty = "orderSagaId")
    public void handle(ProductReserveModel productReserveModel) {
        log.info("product reserve model after call order {}", productReserveModel.getEventId());
    }

}
