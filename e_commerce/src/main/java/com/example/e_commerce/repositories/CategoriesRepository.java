package com.example.e_commerce.repositories;

import com.example.e_commerce.entities.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {
    //No se usa aun
}
