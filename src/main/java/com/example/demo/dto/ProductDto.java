package com.example.demo.dto;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private Category category;
    private int likes;
    private Long user_creater_id;
    private Long sales;



    public ProductDto(String title, BigDecimal price, Category category, int likes, Long user_creater_id) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.likes = likes;
        this.user_creater_id = user_creater_id;
    }

    public static ProductDto valueOf(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory(),
                product.getLikes(),
                product.getUser_creater_id(),
                product.getSales()
        );
    }
    public static List<ProductDto> ListvalueOf(List<Product> products) {
        List<ProductDto> list = new ArrayList<>();
        for(Product i: products) {
            list.add(valueOf(i));
        }
        return list;
    }

    public Product mapToProduct() {
        return new Product(id, title, price, category, likes,user_creater_id,sales);
    }
}
