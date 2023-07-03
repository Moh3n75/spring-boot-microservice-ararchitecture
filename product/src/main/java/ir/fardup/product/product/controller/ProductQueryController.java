package ir.fardup.product.product.controller;

import com.fardup.msutility.json.CustomObjectMapper;
import com.fardup.msutility.search.filter.SearchFilterModel;
import ir.fardup.product.product.service.ProductProjection;
import ir.fardup.product.util.PageResponseType;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.GenericQueryMessage;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryMessage;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public record ProductQueryController(QueryGateway queryGateway) {


    @GetMapping("")
    public Page<ProductModel> listGrid(@RequestParam(name = "searchFilterModel") Optional<String> filter) throws Exception {
         return queryGateway.query(filter.get(),
                new PageResponseType<>(ProductModel.class)).join();
    }
}
