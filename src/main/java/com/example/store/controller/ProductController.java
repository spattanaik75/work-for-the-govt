package com.example.store.controller;

import com.example.store.dto.ProductDTO;
import com.example.store.entity.Product;
import com.example.store.mapper.ProductMapper;
import com.example.store.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productMapper.productsToProductDTOs(productRepository.findAll());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productRepository
                .findById(id)
                .map(productMapper::productToProductDTO)
                .map(productDTO -> ResponseEntity.ok(productDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ProductDTO createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDTO(savedProduct);
    }
}
