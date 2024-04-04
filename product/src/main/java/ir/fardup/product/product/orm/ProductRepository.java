package ir.fardup.product.product.orm;

import com.fardup.msutility.jparepository.repository.JpaSpecificationExecutorWithProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Integer>,
        JpaSpecificationExecutor<Product>, JpaSpecificationExecutorWithProjection<Product, Integer> {

    Product findByTitle(String title);
}
