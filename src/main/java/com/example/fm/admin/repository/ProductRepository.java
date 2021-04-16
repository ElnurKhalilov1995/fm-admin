package com.example.fm.admin.repository;

import com.example.fm.admin.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByNameAndPrice(String name, BigDecimal price);

    List<ProductEntity> findAllByOrderByPriceAsc();

    List<ProductEntity> findAll(Sort sort);

    Page<ProductEntity> findAll(Pageable pageable);
}
