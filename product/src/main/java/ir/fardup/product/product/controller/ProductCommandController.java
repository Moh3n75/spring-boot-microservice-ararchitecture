package ir.fardup.product.product.controller;

import ir.fardup.models.product.command.ProductReserveModel;
import ir.fardup.product.product.controller.model.ProductCreateModel;
import ir.fardup.product.product.controller.model.ProductUpdateModel;
import lombok.extern.java.Log;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@Log
public record ProductCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {


    @PostMapping("")
    public ProductCreateModel create(@RequestBody @Validated ProductCreateModel productCreateModel,
                                     @RequestAttribute("PROCESS-UUID") String processUUID) throws Exception {
        var res = commandGateway.sendAndWait(productCreateModel.toBuilder()
                .eventId(UUID.randomUUID().toString())
                .build());
        productCreateModel.setEventId(processUUID);
        return productCreateModel;
    }

    @PutMapping("/{id}")
    public ProductUpdateModel update(@RequestBody @Validated ProductUpdateModel productUpdateModel, @PathVariable Integer id,
                                     @RequestAttribute("PROCESS-UUID") String processUUID) throws Exception {
        var res = commandGateway.sendAndWait(productUpdateModel.toBuilder()
                .eventId(UUID.randomUUID().toString())
                .id(id)
                .build());
        productUpdateModel.setEventId(processUUID);
        return productUpdateModel;
    }


    @PutMapping("/{id}/reserve")
    public ProductReserveModel reserve(@RequestBody @Validated ProductReserveModel productReserveModel, @PathVariable Integer id,
                                       @RequestAttribute("PROCESS-UUID") String processUUID
    ) throws Exception {
        commandGateway.sendAndWait(productReserveModel.toBuilder()
                .eventId(UUID.randomUUID().toString())
                .productId(id)
                .build());
        productReserveModel.setEventId(processUUID);
        return productReserveModel;
    }


}
