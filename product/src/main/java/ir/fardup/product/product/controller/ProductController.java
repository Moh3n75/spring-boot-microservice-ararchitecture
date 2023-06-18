package ir.fardup.product.product.controller;

import ir.fardup.product.product.service.ProductCommandService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record ProductController(ProductCommandService productCommandService) {


    @PostMapping
    public ProductModel create(@RequestBody @Validated ProductModel productModel) throws Exception {
        return productCommandService.create(productModel);
    }
}
