package ir.fardup.product.product.controller;

import ir.fardup.product.product.service.ProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public record ProductCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {


    @PostMapping("")
    public ProductModel create(@RequestBody @Validated ProductModel productModel) throws Exception {
        var res = commandGateway.sendAndWait(ProductCommand.builder()
                .eventId(UUID.randomUUID().toString())
                .title(productModel.getTitle())
                .quantity(productModel.getQuantity())
                .price(productModel.getPrice())
                .build());


        return productModel;
    }
}
