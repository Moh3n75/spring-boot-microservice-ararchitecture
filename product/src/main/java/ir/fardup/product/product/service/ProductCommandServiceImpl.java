package ir.fardup.product.product.service;

import ir.fardup.product.product.controller.ProductModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final Environment env;

    private final CommandGateway commandGateway;


    @Override
    public ProductModel create(ProductModel productModel) throws Exception {

        var res = commandGateway.sendAndWait(ProductCommand.builder()
                .eventId(UUID.randomUUID().toString())
                .title(productModel.getTitle())
                .quantity(productModel.getQuantity())
                .price(productModel.getPrice())
                .build());


        return productModel;
    }
}
