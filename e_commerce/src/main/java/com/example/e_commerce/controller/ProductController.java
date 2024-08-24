package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CategoryDto;
import com.example.e_commerce.dto.ProductDto;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductDto> getAllProducts() { // GET http://localhost:8090/api/products
        List<Product> products = (List<Product>) productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setProductName(product.getName());
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setCategory_name(product.getCategory().getCategory_name());
            categoryDto.setCategory_type(product.getCategory().getCategory_type());
            productDto.setCategory(categoryDto);
            return productDto;
        }).toList();
        return productDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) { // GET http://localhost:8080/api/products/{id}
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.get().getId());
            productDto.setProductName(product.get().getName());
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.get().getCategory().getId());
            categoryDto.setCategory_name(product.get().getCategory().getCategory_name());
            categoryDto.setCategory_type(product.get().getCategory().getCategory_type());
            productDto.setCategory(categoryDto);
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody Product product) { // POST http://localhost:8080/api/products + JSON
        Product newProduct = productRepository.save(product);
        ProductDto productDto = new ProductDto();
        productDto.setId(newProduct.getId());
        productDto.setProductName(newProduct.getName());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(newProduct.getCategory().getId());
        categoryDto.setCategory_name(newProduct.getCategory().getCategory_name());
        categoryDto.setCategory_type(newProduct.getCategory().getCategory_type());
        productDto.setCategory(categoryDto);
        return productDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) { // PUT http://localhost:8080/api/products/{id} + JSON
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setName(productDetails.getName());
            updatedProduct.setCategory(productDetails.getCategory());
            return ResponseEntity.ok(productRepository.save(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) { // DELETE http://localhost:8080/api/products/{id}
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}