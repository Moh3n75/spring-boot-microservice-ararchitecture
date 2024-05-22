package ir.fardup.product.product.orm;

import com.fardup.msutility.jparepository.repository.JpaSpecificationExecutorWithProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {

    Product findByTitle(String title);

    @Modifying()
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "UPDATE PRODUCT SET QUANTITY = QUANTITY + :quantity WHERE ID = :id", nativeQuery = true)
    void updateIncreaseQuantity(Integer id, Integer quantity);


    @Modifying()
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "UPDATE PRODUCT SET QUANTITY = QUANTITY - :quantity , UPDATE_AT = SYSDATE , UPDATE_PROCESS_UUID = :processUUID  WHERE ID = :id ", nativeQuery = true)
    void updateDecreaseQuantity(Integer id, Integer quantity, String processUUID);
}
