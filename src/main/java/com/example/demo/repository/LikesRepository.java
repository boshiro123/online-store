package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query(value = "SELECT * FROM public.likes WHERE product_id=?;", nativeQuery = true)
    Likes findByProductId(Long id);

    @Query(value = "SELECT * FROM public.likes WHERE user_id=?;", nativeQuery = true)
    List<Likes> findAllByUser_id(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM public.likes WHERE id = ?;", nativeQuery = true)
    void deletebyId(Long id);

}

