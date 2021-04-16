package com.example.fm.admin.controller;

import com.example.fm.admin.entity.ProductEntity;
import com.example.fm.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"", "/"})
    public String findAll(Model model) {
        List<ProductEntity> products = productService.findAll();
        model.addAttribute("products", products);

        return "home";
    }

    @GetMapping("/addNewProduct")
    public String redirectToNewProductPage() {
        return "add_product";
    }

    @GetMapping("/editProduct/{productId}")
    public String redirectToEditProductPage(
            @PathVariable Long productId,
            Model model
    ) {
        ProductEntity product = productService.getProduct(productId);
        model.addAttribute("product", product);

        return "edit_product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(ProductEntity productEntity) {
        productService.saveProduct(productEntity);

        return "redirect:/admin/products/";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return "redirect:/admin/products/";
    }

}
