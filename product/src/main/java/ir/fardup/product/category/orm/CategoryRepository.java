package ir.fardup.product.category.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Integer>,
        JpaSpecificationExecutor<Category> {

    Boolean existsByTitle(String title);

    Category findByCreateProcessUUID(String createProcessUUID);
}
