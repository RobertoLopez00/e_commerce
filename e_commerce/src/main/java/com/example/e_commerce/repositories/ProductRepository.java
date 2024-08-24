package com.example.e_commerce.repositories;

import com.example.e_commerce.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsByName(String name);

}
