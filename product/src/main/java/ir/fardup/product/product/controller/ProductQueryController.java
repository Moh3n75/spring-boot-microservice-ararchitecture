package ir.fardup.product.product.controller;

import ir.fardup.models.product.model.ProductModel;
import ir.fardup.product.util.PageResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public record ProductQueryController(QueryGateway queryGateway) {


    @GetMapping("")
    public Page<ProductModel> listGrid(@RequestParam(name = "searchFilterModel") Optional<String> filter) throws Exception {
        return queryGateway.query(filter.get(),
                new PageResponseType<>(ProductModel.class)).join();
    }

    @GetMapping("/{id}")
    public ProductModel find(@PathVariable() Integer id) throws Exception {
        return queryGateway.query(id, ProductModel.class).join();
    }
}