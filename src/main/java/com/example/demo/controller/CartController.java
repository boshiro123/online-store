package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.service.impl.CartService;
import com.example.demo.service.impl.HistoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final HistoryService historyService;
    @Autowired
    public CartController(CartService cartService, HistoryService historyService) {
        this.cartService = cartService;
        this.historyService = historyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartDto> findAll() {
        return cartService.findAll();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartDto> geMytCart(@PathVariable("id") Long id) {
        return cartService.findByUserId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить товар в корзину")
    public void add(@RequestBody CartDto product) {
        System.out.println(product.toString());
        System.out.println(cartService.add(product));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Удалить товар")
    public void delete(@PathVariable("id") Long id) {
        cartService.delete(id);
    }

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public void find(@RequestBody List<CartDto> list) {
        historyService.Buy(list);
    }
}
