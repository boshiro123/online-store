package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.cart SET count = ? WHERE id=?;", nativeQuery = true)
    void update(int count, Long id);
    @Query(value = "SELECT * FROM public.cart WHERE user_id=?;", nativeQuery = true)
    List<Cart> findAllByUser_id(Long id);
    @Query(value = "SELECT * FROM public.cart WHERE product_id=?;", nativeQuery = true)
    Cart findByProductId(Long id);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM public.cart WHERE user_id=?;", nativeQuery = true)
    void deleteByUserId(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM public.cart WHERE product_id=?;", nativeQuery = true)
    void deleteByProductId(Long id);
}

