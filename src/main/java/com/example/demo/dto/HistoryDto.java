package com.example.demo.dto;

import com.example.demo.entity.Cart;
import com.example.demo.entity.History;
import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {
    private Long id;
    private Long user_id;
    private Product product;
    private int count;
    private LocalDate date;


    public static HistoryDto valueOf(History history) {
        return new HistoryDto(
                history.getId(),
                history.getUser_id(),
                history.getProduc(),
                history.getCount(),
                history.getDate()
        );
    }

    public HistoryDto(Long user_id, Product product, int count, LocalDate date) {
        this.user_id = user_id;
        this.product = product;
        this.count = count;
        this.date = date;
    }

    public History mapTo() {
        return new History(id, user_id,product,count,date);
    }

    public static List<HistoryDto> ListvalueOf(List<History> histories) {
        List<HistoryDto> list = new ArrayList<>();
        for(History i: histories) {
            list.add(valueOf(i));
        }
        return list;
    }


}
