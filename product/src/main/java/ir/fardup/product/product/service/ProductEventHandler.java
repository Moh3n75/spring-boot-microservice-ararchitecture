package ir.fardup.product.product.service;

import com.fardup.msutility.customexception.BusinessException;
import ir.fardup.product.category.aggregate.CategoryEventHandler;
import ir.fardup.product.product.controller.model.ProductCreateModel;
import ir.fardup.product.product.controller.model.ProductUpdateModel;
import ir.fardup.product.product.orm.Product;
import ir.fardup.product.product.orm.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
public class ProductEventHandler {

    private final ProductRepository productRepository;

    private final CategoryEventHandler categoryEventHandler;

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductCreateModel productCreateModel) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productCreateModel, product);
        productRepository.save(product);
    }


    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductUpdateModel productUpdateModel) throws Exception {
        Product product = productRepository.findById(productUpdateModel.getId())
                .orElseThrow();
        BeanUtils.copyProperties(productUpdateModel, product);
        productRepository.save(product);
    }


}
