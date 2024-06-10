package com.example.demo.repository;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findByTitle(String title);
    @Query(value = "SELECT * FROM public.products WHERE id = ?;", nativeQuery = true)
    Product findByID(Long id);
    @Query(value = "SELECT * FROM public.products WHERE user_creater_id=?;", nativeQuery = true)
    List<Product> findByUser_creater_id(Long id);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.products SET likes = ? WHERE id=?;", nativeQuery = true)
    void plusLike(int likes, Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.products SET price = ? WHERE id=?;", nativeQuery = true)
    void updatePrice(BigDecimal price, Long id);
}

