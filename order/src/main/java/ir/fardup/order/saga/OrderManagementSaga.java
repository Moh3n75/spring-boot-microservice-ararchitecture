package ir.fardup.order.saga;

import com.fardup.msutility.axon.RequestInfo;
import ir.fardup.models.payment.OrderPaymentModel;
import ir.fardup.models.product.command.ProductReserveModel;
import ir.fardup.models.product.model.ProductModel;
import ir.fardup.order.order.controller.model.OrderCreateModel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

@Saga
@Slf4j
@ProcessingGroup("order-saga")
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderSagaId")
    public void handle(OrderCreateModel orderCreateModel, @MetaDataValue("processUUID") String processUUID,
                       @MetaDataValue("requestInfo") HashMap<String, String> requestInfo) {
        log.info("Starting order management saga for order with ID: {}", orderCreateModel.getOrderSagaId());
        // Send commands using the CommandGateway
        ProductReserveModel productReserveModel = ProductReserveModel.builder()
                .eventId(UUID.randomUUID().toString())
                .quantity(orderCreateModel.getQuantity())
                .productId(25)
                .orderSagaId(orderCreateModel.getOrderSagaId())
                .build();
        log.info("request info {}", RequestInfo.getHeaders());
        commandGateway.sendAndWait(productReserveModel/*, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Handle command execution failure
                log.error("Failed to reserve product: {}", commandResultMessage.exceptionResult());
            } else {
                // Handle successful command execution
                log.info("Product reserved successfully.");
            }
        }*/);
    }

    @SagaEventHandler(associationProperty = "orderSagaId")
    public void handle(ProductReserveModel productReserveModel) {

        ProductModel productModel = queryGateway.query("product-find", productReserveModel.getProductId(), ProductModel.class).join();
        log.info("product reserve model after call order {}", productReserveModel.getEventId());
    }


    @SagaEventHandler(associationProperty = "orderSagaId")
    public void handle(OrderPaymentModel orderPaymentModel) {
        log.info("another saga request");
    }

}

