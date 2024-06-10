package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.History;
import com.example.demo.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {

    @Query(value = "SELECT * FROM public.history WHERE product_id=?;", nativeQuery = true)
    History findByProductId(Long id);

    @Query(value = "SELECT * FROM public.history WHERE product_id=?;", nativeQuery = true)
    History findByProduc_(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.history SET count = ?, date = ? WHERE id=?;", nativeQuery = true)
    void update(int count, LocalDate date, Long id);

    @Query(value = "SELECT * FROM public.history WHERE user_id = ?;", nativeQuery = true)
    List<History> findAllByUser_id(Long id);


}
