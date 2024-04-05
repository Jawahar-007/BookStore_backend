package com.jawa.bookstoreapp.cart.repository;

import com.jawa.bookstoreapp.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Long> {
    @Query("SELECT c from CartEntity c where c.user.id = :userId")
    List<CartEntity> findByUserId(Long userId);
}
