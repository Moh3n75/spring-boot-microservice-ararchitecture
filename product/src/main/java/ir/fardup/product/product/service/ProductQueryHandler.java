package ir.fardup.product.product.service;

import com.fardup.msutility.json.CustomObjectMapper;
import ir.fardup.product.product.controller.model.ProductModel;
import ir.fardup.product.product.controller.model.ProductRequestModel;
import ir.fardup.product.product.orm.Product;
import ir.fardup.product.product.orm.ProductRepository;
import ir.fardup.product.product.orm.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public Page<ProductModel> listGrid(String filter) throws Exception {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        ProductRequestModel productRequestModel = customObjectMapper.readValue(filter, ProductRequestModel.class);
        return productRepository
                .findAll(productRequestModel.makeSpecification(new ProductSpecification()), productRequestModel.makePageable())
                .map(this::convertEntityToModel);
    }

    @QueryHandler
    public ProductModel find(Integer id) throws Exception {
        return convertEntityToModel(productRepository.findById(id)
                .orElseThrow());
    }


    ProductModel convertEntityToModel(Product product) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(product, productModel);
        return productModel;
    }
}
