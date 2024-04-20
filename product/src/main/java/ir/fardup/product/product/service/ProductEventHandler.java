package ir.fardup.product.product.service;

import ir.fardup.models.product.command.ProductReserveModel;
import ir.fardup.product.category.orm.Category;
import ir.fardup.product.category.orm.CategoryRepository;
import ir.fardup.product.product.controller.model.ProductCreateModel;
import ir.fardup.product.product.controller.model.ProductUpdateModel;
import ir.fardup.product.product.orm.Product;
import ir.fardup.product.product.orm.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
@Slf4j
public class ProductEventHandler {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductCreateModel productCreateModel, @MetaDataValue("processUUID") String processUUID) throws Exception {
        log.info("request context is {}", RequestContextHolder.getRequestAttributes());
        Product product = new Product();
        Category category =
                categoryRepository.findByCreateProcessUUID(processUUID);
        BeanUtils.copyProperties(productCreateModel, product);
        product.setCategory(category);
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

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void reserve(ProductReserveModel productReserveModel,@MetaDataValue("processUUID") String processUUID) throws Exception {
        productRepository.updateDecreaseQuantity(productReserveModel.getProductId(), productReserveModel.getQuantity(),processUUID);
    }

}
