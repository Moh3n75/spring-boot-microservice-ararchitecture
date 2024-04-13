package com.fardup.order.order.orm;

import com.fardup.msutility.jparepository.repository.JpaSpecificationExecutorWithProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>,
        JpaSpecificationExecutor<Order>, JpaSpecificationExecutorWithProjection<Order, Integer> {
}
