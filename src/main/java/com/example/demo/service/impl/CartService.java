package com.example.demo.service.impl;


import com.example.demo.dto.CartDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
public class CartService{
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartDto> findAll() {
        List<CartDto> result = CartDto.ListvalueOf(cartRepository.findAll());
        return result;
    }

    public List<CartDto> findByUserId(Long id) {
        List<CartDto> result = CartDto.ListvalueOf(cartRepository.findAllByUser_id(id));
        return result;
    }
    public boolean add(CartDto product) {
            Cart cart = cartRepository.findByProductId(product.getProduct().getId());
            if(cart==null){
                System.out.println("Сработало\n");
                Product product1 = productRepository.findById(product.getProduct().getId())
                        .orElseThrow(() -> new ProductNotFoundException(product.getProduct().getId()));
                product.setProduct(product1);
                cartRepository.save(product.mapTo());
                return false;
            }
            cartRepository.update(CartDto.valueOf(cart).getCount()+product.getCount(),CartDto.valueOf(cart).getId());

        return true;
    }
  public String delete(Long id) {
        log.debug("Очистить корзину");
        cartRepository.deleteByUserId(id);
        return "Удалено";
  }

}
