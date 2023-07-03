package ir.fardup.product.product.service;

import com.fardup.msutility.json.CustomObjectMapper;
import com.fardup.msutility.search.filter.SearchFilterModel;
import ir.fardup.product.product.controller.ProductModel;
import ir.fardup.product.product.controller.ProductRequestModel;
import ir.fardup.product.product.orm.Product;
import ir.fardup.product.product.orm.ProductRepository;
import ir.fardup.product.product.orm.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public Page<ProductModel> listGrid(String filter) throws Exception {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        ProductRequestModel productRequestModel = customObjectMapper.readValue(filter, ProductRequestModel.class);
        return productRepository.findAll(new ProductSpecification(), productRequestModel.makePageable()).map(this::convertEntityToModel);
    }


    ProductModel convertEntityToModel(Product product) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(product, productModel);
        return productModel;
    }
}
