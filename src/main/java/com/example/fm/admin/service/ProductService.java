package com.example.fm.admin.service;

import com.example.fm.admin.entity.ProductEntity;
import com.example.fm.admin.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public ProductEntity getProduct(Long id) {
        return productRepository.findById(id).orElseGet(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity getProductByNameAndPrice(String name, BigDecimal price) {
        return productRepository.findByNameAndPrice(name, price).orElse(null);
    }

    public List<ProductEntity> getProductsAndOrderByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<ProductEntity> getProducts(String direction, String property) {

        Sort sort = direction.equals("asc")
                ? Sort.by(Sort.Direction.ASC, property)
                : Sort.by(Sort.Direction.DESC, property);

        return productRepository.findAll(sort);
    }

    public Page<ProductEntity> getProducts(Pageable pageable) {

        return productRepository.findAll(pageable);
    }
}
