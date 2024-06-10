package com.example.demo.service.impl;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.HistoryDto;
import com.example.demo.dto.LikesDto;
import com.example.demo.dto.OrdersDto;
import com.example.demo.entity.History;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final ProductRepository productRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository, ProductRepository productRepository) {
        this.historyRepository = historyRepository;
        this.productRepository = productRepository;
    }

    public void Buy(List<CartDto> list){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        for(CartDto i: list){
            System.out.println(i.toString());

            History founding = historyRepository.findByProductId(i.getProduct().getId());
            if(founding==null){
                Product product1 = productRepository.findById(i.getProduct().getId())
                        .orElseThrow(() -> new ProductNotFoundException(i.getProduct().getId()));
            History history = new History(i.getUser_id(),product1,i.getCount(),localDate);
            historyRepository.save(history);
            }
            else{
                System.out.println("UPDATE IS WORKING");
                historyRepository.update(i.getCount()+HistoryDto.valueOf(founding).getCount(), localDate, founding.getId());
            }
        }
    }

    public void AddToHistory(OrdersDto ordersDto){
        Product product = productRepository.findByTitle(ordersDto.getProduct_name());
        History newHistory = new History(ordersDto.getUser_id(),product,1,getCurrentDate());
        History founding = historyRepository.findByProductId(product.getId());
        if(founding==null){
            historyRepository.save(newHistory);
        }
        else{
            System.out.println("UPDATE IS WORKING");
            historyRepository.update(founding.getCount()+1,getCurrentDate(),founding.getId());
        }

    }

    public List<HistoryDto> findByUserId(Long id) {
        List<HistoryDto> result = HistoryDto.ListvalueOf(historyRepository.findAllByUser_id(id));
        System.out.println(id);
        return result;
    }
    public static LocalDate getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        return LocalDate.parse(formattedDate, formatter);
    }
}

