package ir.fardup.order.order.controller;

import com.fardup.msutility.axon.RequestInfo;
import ir.fardup.order.order.controller.model.OrderCreateModel;
import ir.fardup.order.order.controller.model.OrderFindQueryModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public record OrderCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {


    @PostMapping()
    public OrderCreateModel create(@RequestBody OrderCreateModel orderCreateModel) {

        String eventId = UUID.randomUUID().toString();


        SubscriptionQueryResult<OrderCreateModel, OrderCreateModel> queryResult = queryGateway.subscriptionQuery(
                new OrderFindQueryModel(RequestInfo.getHeader("PROCESS-UUID")), ResponseTypes.instanceOf(OrderCreateModel.class),
                ResponseTypes.instanceOf(OrderCreateModel.class));

        commandGateway.sendAndWait(orderCreateModel.toBuilder().eventId(UUID.randomUUID().toString())
                .OrderSagaId(RequestInfo.getHeader("PROCESS-UUID"))
                .build());

        return orderCreateModel;
    }
}
