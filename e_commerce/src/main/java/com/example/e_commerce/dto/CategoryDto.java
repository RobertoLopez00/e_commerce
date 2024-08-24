package com.example.e_commerce.dto;

public class CategoryDto {

    private Long id;

    private String category_name;

    private String category_type;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String category_name, String category_type) {
        this.id = id;
        this.category_name = category_name;
        this.category_type = category_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }
}
