package com.example.demo.service.impl;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.LikesDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Likes;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.LikesRepository;
import com.example.demo.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class LikesService {
    private final LikesRepository likesRepository;
    private final ProductRepository productRepository;

    @Autowired
    public LikesService(LikesRepository likesRepository, ProductRepository productRepositoryl) {
        this.likesRepository = likesRepository;
        this.productRepository = productRepositoryl;
    }

    public void add(Long product_id, Long user_id) {
            Product product1 = productRepository.findById(product_id)
                    .orElseThrow(() -> new ProductNotFoundException(product_id));
            Likes newLike = new Likes(user_id,product1);
            likesRepository.save(newLike);
    }

    public List<LikesDto> findByUserId(Long id) {
        List<LikesDto> result = LikesDto.ListvalueOf(likesRepository.findAllByUser_id(id));
        System.out.println(id);
        return result;
    }
    public void delete(Long id){
        likesRepository.deletebyId(id);
    }
}
