package ir.fardup.product.category.aggregate;

import ir.fardup.product.category.controller.CategoryCreateModel;
import ir.fardup.product.category.orm.Category;
import ir.fardup.product.category.orm.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@ProcessingGroup("category-group")
public class CategoryEventHandler {

    private final CategoryRepository categoryRepository;

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public Integer create(CategoryCreateModel categoryCreateModel) throws Exception {
        Category category = new Category();
        BeanUtils.copyProperties(categoryCreateModel, category);
        Category c = categoryRepository.save(category);
        return c.getId();
    }
}
