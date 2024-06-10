package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
    @Query(value = "SELECT * FROM public.users WHERE name=?;", nativeQuery = true)
    User findUserByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.users SET status_id = 2 WHERE id=?;", nativeQuery = true)
    void becomeSeller(Long id);
}
