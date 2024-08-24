package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CategoryDto;
import com.example.e_commerce.entities.Categories;
import com.example.e_commerce.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return ((List<Categories>) categoriesRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Optional<Categories> category = categoriesRepository.findById(id);
        return category.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        Categories category = convertToEntity(categoryDto);
        Categories savedCategory = categoriesRepository.save(category);
        return convertToDto(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Optional<Categories> category = categoriesRepository.findById(id);
        if (category.isPresent()) {
            Categories updatedCategory = category.get();
            updatedCategory.setCategory_name(categoryDto.getCategory_name());
            updatedCategory.setCategory_type(categoryDto.getCategory_type());
            return ResponseEntity.ok(convertToDto(categoriesRepository.save(updatedCategory)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Optional<Categories> category = categoriesRepository.findById(id);
        if (category.isPresent()) {
            categoriesRepository.delete(category.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private CategoryDto convertToDto(Categories category) {
        return new CategoryDto(category.getId(), category.getCategory_name(), category.getCategory_type());
    }

    private Categories convertToEntity(CategoryDto categoryDto) {
        Categories category = new Categories();
        category.setId(categoryDto.getId());
        category.setCategory_name(categoryDto.getCategory_name());
        category.setCategory_type(categoryDto.getCategory_type());
        return category;
    }
}