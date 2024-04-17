package ir.fardup.order.saga;

import com.fardup.msutility.axon.RequestInfo;
import ir.fardup.models.product.ProductReserveModel;
import ir.fardup.order.order.controller.model.OrderCreateModel;
import jakarta.persistence.Transient;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

@Saga
@Slf4j
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderSagaId")
    public void handle(OrderCreateModel orderCreateModel, @MetaDataValue("processUUID") String processUUID,
                       @MetaDataValue("requestInfo") HashMap<String, String> requestInfo) {
        log.info("Starting order management saga for order with ID: {}", orderCreateModel.getOrderSagaId());
        // Send commands using the CommandGateway
        SagaLifecycle.associateWith("orderSagaId",orderCreateModel.getOrderSagaId());
        ProductReserveModel productReserveModel = ProductReserveModel.builder()
                .eventId(UUID.randomUUID().toString())
                .quantity(1)
                .productId(10)
                .orderSagaId(orderCreateModel.getOrderSagaId())
                .build();
        commandGateway.send(productReserveModel, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Handle command execution failure
                log.error("Failed to reserve product: {}", commandResultMessage.exceptionResult());
            } else {
                // Handle successful command execution
                log.info("Product reserved successfully.");
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderSagaId")
    public void handle(ProductReserveModel productReserveModel) {
        log.info("product reserve model after call order {}", productReserveModel.getEventId());
    }

}
