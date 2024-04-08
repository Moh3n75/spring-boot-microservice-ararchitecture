package ir.fardup.product.product.controller;

import com.fardup.msutility.axon.RequestInfo;
import ir.fardup.product.product.controller.model.ProductCreateModel;
import ir.fardup.product.product.controller.model.ProductUpdateModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@Log
public record ProductCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {


    @PostMapping("")
    public ProductCreateModel create(@RequestBody @Validated ProductCreateModel productCreateModel) throws Exception {
        log.info(RequestInfo.getRequest().toString());
        var res = commandGateway.sendAndWait(productCreateModel.toBuilder()
                .eventId(UUID.randomUUID().toString())
                .build());
        productCreateModel.setEventId(res.toString());
        return productCreateModel;
    }

    @PutMapping("/{id}")
    public ProductUpdateModel update(@RequestBody @Validated ProductUpdateModel productUpdateModel, @PathVariable Integer id) throws Exception {
        var res = commandGateway.sendAndWait(productUpdateModel.toBuilder()
                .eventId(UUID.randomUUID().toString())
                .id(id)
                .build());
        productUpdateModel.setEventId(res.toString());
        return productUpdateModel;
    }



}
