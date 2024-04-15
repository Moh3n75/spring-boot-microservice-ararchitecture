package ir.fardup.order.order.controller;

import ir.fardup.order.order.controller.model.OrderCreateModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public record OrderCommandController(CommandGateway commandGateway) {


    @PostMapping()
    public OrderCreateModel create(@RequestBody  OrderCreateModel orderCreateModel) {
        commandGateway.sendAndWait(orderCreateModel.toBuilder().eventId(UUID.randomUUID().toString()).build());
        return orderCreateModel;
    }
}
