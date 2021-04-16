package com.example.fm.admin.controller;

import com.example.fm.admin.entity.ProductEntity;
import com.example.fm.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{name}/{price}")
    public ProductEntity getProductByNameAndPrice(@PathVariable String name, @PathVariable BigDecimal price) {
        ProductEntity productEntity = productService.getProductByNameAndPrice(name, price);
        return productEntity;
    }

    @GetMapping({"", "/"})
    public List<ProductEntity> getProducts() {
        return productService.getProductsAndOrderByPrice();
    }

    @GetMapping({"", "/withSort"})
    public List<ProductEntity> getProductsWithSort(
            @RequestParam(name = "direction") String direction,
            @RequestParam(name = "property") String property) {
        return productService.getProducts(direction, property);
    }

    @GetMapping({"", "/withPage"})
    public Page<ProductEntity> getProductsWithPage(
            @PageableDefault(sort = "price",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.getProducts(pageable);
    }
}
