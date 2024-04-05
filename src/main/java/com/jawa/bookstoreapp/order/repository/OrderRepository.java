package com.jawa.bookstoreapp.order.repository;

import com.jawa.bookstoreapp.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCancelIsFalse();

    @Query("SELECT o from OrderEntity o where o.cart.user.id = : userId")
    List<OrderEntity>  findByUserId(@Param("userId") Long userId);
}
