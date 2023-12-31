package ir.fardup.product.product.service;

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

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent, product);
        productRepository.save(product);
    }

}
