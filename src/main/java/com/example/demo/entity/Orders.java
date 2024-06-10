package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_number")
    private int order_number;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "cost")
    private int cost;
    @Column(name = "order_status")
    private String order_status;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "seller_id")
    private Long seller_id;

    public Orders(int order_number, String product_name, int cost, String order_status, Long user_id, Long seller_id) {
        this.order_number = order_number;
        this.product_name = product_name;
        this.cost = cost;
        this.order_status = order_status;
        this.user_id = user_id;
        this.seller_id = seller_id;
    }
}
