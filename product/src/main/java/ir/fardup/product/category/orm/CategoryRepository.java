package ir.fardup.product.category.orm;

import com.fardup.msutility.jparepository.repository.JpaSpecificationExecutorWithProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Integer>,
        JpaSpecificationExecutor<Category>, JpaSpecificationExecutorWithProjection<Category, Integer> {

    Boolean existsByTitle(String title);
}
