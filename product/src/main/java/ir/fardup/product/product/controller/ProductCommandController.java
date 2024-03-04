package ir.fardup.product.product.controller;

import ir.fardup.product.product.controller.model.ProductModel;
import ir.fardup.product.product.controller.model.ProductUpdateModel;
import ir.fardup.product.product.service.ProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ProductUpdateModel update(@RequestBody @Validated ProductUpdateModel productUpdateModel, @PathVariable Integer id) throws Exception {
        var res = commandGateway.sendAndWait(productUpdateModel.toBuilder()
                .eventId(UUID.randomUUID().toString())
                .id(id)
                .build());
        return productUpdateModel;
    }


}
