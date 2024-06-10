package com.example.demo.dto;

import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {

    private Long id;
    private int order_number;
    private String product_name;
    private int cost;
    private String order_status;
    private Long user_id;
    private Long seller_id;

    public static OrdersDto valueOf(Orders order) {
        return new OrdersDto(
                order.getId(),
                order.getOrder_number(),
                order.getProduct_name(),
                order.getCost(),
                order.getOrder_status(),
                order.getUser_id(),
                order.getSeller_id()
        );
    }
    public static List<OrdersDto> ListvalueOf(List<Orders> orders) {
        List<OrdersDto> list = new ArrayList<>();
        for(Orders i: orders) {
            list.add(valueOf(i));
        }
        return list;
    }

    public Orders mapToOrder() {
        return new Orders(id, order_number,product_name,cost,order_status,user_id,seller_id);
    }
}
