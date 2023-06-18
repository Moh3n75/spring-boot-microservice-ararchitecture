package ir.fardup.product.product.service;

import ir.fardup.product.product.controller.ProductModel;

public interface ProductCommandService {
    ProductModel create(ProductModel productModel) throws Exception;
}
