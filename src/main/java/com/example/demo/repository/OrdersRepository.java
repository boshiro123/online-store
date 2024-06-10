package com.example.demo.repository;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT * FROM public.orders WHERE user_id=?;", nativeQuery = true)
    List<Orders> findAllByUser_id(Long id);
    @Query(value = "SELECT * FROM public.orders WHERE seller_id=?;", nativeQuery = true)
    List<Orders> findAllBySeller_id(Long id);
}
