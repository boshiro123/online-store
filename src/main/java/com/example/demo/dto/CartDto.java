package com.example.demo.dto;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Long user_id;
    private Product product;
    private int count;



    public static CartDto valueOf(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser_id(),
                cart.getProduct(),
                cart.getCount()
        );
    }

    public Cart mapTo() {
        return new Cart(id, user_id, product,count);
    }

    public static List<CartDto> ListvalueOf(List<Cart> carts) {
        List<CartDto> list = new ArrayList<>();
        for(Cart i: carts) {
            list.add(valueOf(i));
        }
        return list;
    }


}
