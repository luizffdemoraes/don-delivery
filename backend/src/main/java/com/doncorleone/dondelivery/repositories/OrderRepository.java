package com.doncorleone.dondelivery.repositories;

import com.doncorleone.dondelivery.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long>{
/*
    @Query("SELECT DISTINCT obj FROM Order obj JOIN FETCH obj.products"
            + " WHERE obj.status = 0 ORDER BY obj.moment ASC")
    List<Order> findOrderWithProducts();
 */
}