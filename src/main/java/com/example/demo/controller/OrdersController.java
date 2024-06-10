package com.example.demo.controller;

import com.example.demo.dto.LikesDto;
import com.example.demo.dto.OrdersDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.impl.OrdersService;
import com.example.demo.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void doOrder(@RequestBody @Valid List<OrdersDto> orders) {
        System.out.println("___________doOrder____________");
        System.out.println(orders);
        ordersService.addOrders(orders);
    }

    @PutMapping("/editOrder")
    @ResponseStatus(HttpStatus.OK)
    public void editOrder(@RequestBody @Valid OrdersDto order) {
        ordersService.editOrder(order);

    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdersDto> getUserOrders(@PathVariable("id") Long id) {
        System.out.println("___________getUserOrders____________");
        return ordersService.getUserOrders(id);
    }

    @GetMapping("/seller/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdersDto> getSellerOrders(@PathVariable("id") Long id) {
        System.out.println("___________getSellerOrders____________");
        return ordersService.getSellerOrders(id);
    }

    @PutMapping("/takeOrder")
    @ResponseStatus(HttpStatus.OK)
    public void takeOrder(@RequestBody @Valid OrdersDto order) {
        System.out.println("___________takeOrder____________");
        System.out.println(order);
        ordersService.deleteOrder(order);
    }
}
