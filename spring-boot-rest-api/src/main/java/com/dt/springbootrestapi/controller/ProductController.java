package com.dt.springbootrestapi.controller;

import com.dt.springbootrestapi.entity.Product;
import com.dt.springbootrestapi.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products/{id}")
    Product getById(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    @GetMapping("/products")
    List<Product> all() {
        return productRepository.findAll();
    }

    @PostMapping("/product")
    Product addProduct(@Valid @RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @PutMapping("/product/{id}")
    Product updateOrCreate(@RequestBody Product productData, @PathVariable long id) {
        return productRepository.findById(id)
                .map(product -> {product.setName(productData.getName());
                return productRepository.save(product);
                })
                .orElseGet(() -> {
                    productData.setId(id);
                    return productRepository.save(productData);
                });
    }
}
