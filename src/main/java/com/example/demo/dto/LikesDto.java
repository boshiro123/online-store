package com.example.demo.dto;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Likes;
import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesDto {
    private Long id;
    private Long user_id;
    private Product product;


    public static LikesDto valueOf(Likes like) {
        return new LikesDto(
                like.getId(),
                like.getUser_id(),
                like.getProd()
        );
    }

    public Likes mapTo() {
        return new Likes(id, user_id, product);
    }

    public static List<LikesDto> ListvalueOf(List<Likes> likes) {
        List<LikesDto> list = new ArrayList<>();
        for(Likes i: likes) {
            list.add(valueOf(i));
        }
        return list;
    }


}
