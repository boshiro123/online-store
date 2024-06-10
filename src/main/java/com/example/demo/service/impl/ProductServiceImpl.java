package com.example.demo.service.impl;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductDuplicateException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.specification.ProductSpecification;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl  {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.cartRepository = cartRepository;
    }

    private final static int PAGE_SIZE = 6;

    public Page<ProductDto> findProductPage(MultiValueMap<String, String> params, Integer pageIndex) {
        log.debug("Получить страницу с товарами");
        log.debug("  параметры для фильтрации товаров: " + params);
        log.debug("  номер страницы: " + pageIndex);

        // Создать спецификацию и передать её в какой-либо метод интерфейса JpaSpecificationExecutor
        Specification<Product> spec = ProductSpecification.build(params);
        Pageable pageable = PageRequest.of(pageIndex - 1, PAGE_SIZE); // (pageIndex - 1) потому что тут нумерация с 0, а на фронте с 1
        Page<ProductDto> page = productRepository.findAll(spec, pageable).map(it -> ProductDto.valueOf(it));
        log.debug("  список товаров на искомой странице: " + page.getContent());
        log.debug("  всего товаров на странице: " + page.getContent().size());
        return page;
    }

    public Page<ProductDto> findProductPageSorted(MultiValueMap<String, String> params, Integer pageIndex) {
        log.debug("Получить страницу с товарами");
        log.debug("  параметры для фильтрации товаров: " + params);
        log.debug("  номер страницы: " + pageIndex);

        // Создать спецификацию и передать её в какой-либо метод интерфейса JpaSpecificationExecutor
        Specification<Product> spec = ProductSpecification.build(params);
        Pageable pageable = PageRequest.of(pageIndex - 1, PAGE_SIZE, Sort.by("likes").descending()); // (pageIndex - 1) потому что тут нумерация с 0, а на фронте с 1
        Page<ProductDto> page = productRepository.findAll(spec, pageable).map(it -> ProductDto.valueOf(it));
        log.debug("  список товаров на искомой странице: " + page.getContent());
        log.debug("  всего товаров на странице: " + page.getContent().size());
        return page;
    }
    public List<ProductDto> getMyProducts(Long id){
        return ProductDto.ListvalueOf(productRepository.findByUser_creater_id(id));
    }


    public ProductDto findById(Long id) {
        ProductDto productDto = productRepository.findById(id)
                .map(it -> ProductDto.valueOf(it))
                .orElseThrow(() -> new ProductNotFoundException(id));

        log.debug("По id=" + id + " получен товар: " + productDto);
        return productDto;
    }

    public ProductDto save(ProductDto productDto) {
        log.debug("Сохранить новый товар в БД");
        log.debug("  productDto: " + productDto);

        String title = productDto.getTitle();
        if (productRepository.findByTitle(title) != null) {
            throw new ProductDuplicateException(title);
        }
        String name = productDto.getCategory().getName();
        Category category = categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotFoundException(name));
        log.debug("  category: " + category);

        Product product = productDto.mapToProduct();
        log.debug("  product: " + product);
        product.setCategory(category);
        product.setSales(0L);
        log.debug("  product after set category: " + product);

        Product savedProduct = productRepository.save(product);
        log.debug("  savedProduct: " + savedProduct);

        ProductDto savedProductDto = ProductDto.valueOf(savedProduct);
        log.debug("  в БД сохранён новый товар: " + savedProductDto);
        return savedProductDto;
    }

    public void addSales(List<CartDto> productDtos){
        for(CartDto i: productDtos){
            Product product = i.getProduct();
            product.setSales(product.getSales()+1);
            productRepository.save(product);
        }
    }

    public void update(Long id, ProductDto productDto) {
        log.debug("Обновить товар с id: " + id);

        productRepository.updatePrice(productDto.getPrice(),productDto.getId());
    }
    public void plusLike(Long id){
        Product currentProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.plusLike(currentProduct.getLikes() + 1 ,id);
    }

    public void delete(Long id) {
        cartRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }

    public List<String> getLabels(Long id){
        List<ProductDto> list = ProductDto.ListvalueOf(productRepository.findByUser_creater_id(id));
        List<String> labels = new ArrayList<>();
        for(ProductDto i: list){
            labels.add(i.getTitle());
        }
        return labels;
    }
    public List<Double> getData(Long id){
        List<ProductDto> list = ProductDto.ListvalueOf(productRepository.findByUser_creater_id(id));
        List<Double> data = new ArrayList<>();
        for(ProductDto i: list){
            data.add(i.getSales().doubleValue());
        }
        return data;
    }
}
