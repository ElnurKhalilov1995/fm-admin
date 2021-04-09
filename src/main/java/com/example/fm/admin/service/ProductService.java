package com.example.fm.admin.service;

import com.example.fm.admin.entity.ProductEntity;
import com.example.fm.admin.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll () {
        return productRepository.findAll();
    }

    public void saveProduct (ProductEntity productEntity) {
        productRepository.save(productEntity);
    }
}
