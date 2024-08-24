package com.example.e_commerce.dto;

public class ProductDto {
    private Long id;
    private String productName;
    private CategoryDto category;

    public ProductDto() {
    }

    public ProductDto(Long id, String productName, CategoryDto category) {
        this.id = id;
        this.productName = productName;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(CategoryDto category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategory_name(category.getCategory_name());
        categoryDto.setCategory_type(category.getCategory_type());
        this.category = categoryDto;
    }
}