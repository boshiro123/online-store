package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.OrdersDto;
import com.example.demo.dto.ProductDto;
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
@RequestMapping("/api/v1/product")
@Api(description = "Контроллер для товаров")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить страницу с товарами")
    public Page<ProductDto> findProductPage(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam("pageIndex") Integer pageIndex) {
        return productService.findProductPage(params, pageIndex);
    }

    @GetMapping("/sort")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить страницу с товарами")
    public Page<ProductDto> findProductSorted(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam("pageIndex") Integer pageIndex
    ) {
        return productService.findProductPageSorted(params, pageIndex);
    }
    @GetMapping("/my/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить страницу с товарами")
    public List<ProductDto> getMyProducts(@PathVariable("id") Long id) {
        return productService.getMyProducts(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получить товар по id")
    public ProductDto findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить новый товар в каталог")
    public ProductDto save(@RequestBody @Valid ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Обновить товар")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid ProductDto productDto) {
        productService.update(id, productDto);
    }
    @PutMapping("/like/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void plusLike(@PathVariable("id") Long id) {
        productService.plusLike(id);
    }

    @PutMapping("/sales")
    @ResponseStatus(HttpStatus.OK)
    public void doOrder(@RequestBody @Valid List<CartDto> productDtos) {
        System.out.println(productDtos);
        productService.addSales(productDtos);

    }

    @GetMapping("/labels/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getLabels(@PathVariable Long id){
        return productService.getLabels(id);
    }
    @GetMapping("/data/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Double> getData(@PathVariable Long id){
        return productService.getData(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Удалить товар")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
