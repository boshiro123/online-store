package com.example.demo.service.impl;

import com.example.demo.dto.OrdersDto;
import com.example.demo.entity.Orders;
import com.example.demo.repository.OrdersRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Log4j2
public class OrdersService {
     private OrdersRepository ordersRepository;
    private HistoryService historyService;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, HistoryService historyService) {
        this.ordersRepository = ordersRepository;
        this.historyService = historyService;
    }



    public void addOrders(List<OrdersDto> ordersDtoList){
         for(OrdersDto i: ordersDtoList){
             i.setOrder_status("На складе");
             i.setOrder_number(generateRandomNumber(10000,99999));
             ordersRepository.save(i.mapToOrder());
         }
    }
    public List<OrdersDto> getUserOrders(Long id){
         List<Orders> orders = ordersRepository.findAllByUser_id(id);
         return  OrdersDto.ListvalueOf(orders);
    }
    public List<OrdersDto> getSellerOrders(Long id){
        List<Orders> orders = ordersRepository.findAllBySeller_id(id);
        return  OrdersDto.ListvalueOf(orders);
    }
    public void editOrder(OrdersDto ordersDto){
         ordersRepository.save(ordersDto.mapToOrder());
    }
    public static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Минимальное значение должно быть меньше максимального");
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void deleteOrder(OrdersDto ordersDto){
        historyService.AddToHistory(ordersDto);
         ordersRepository.delete(ordersDto.mapToOrder());
    }


}
