package com.example.e_commerce.dto;

public class DeliveryDto {
    private Long id;
    private String type;
    private String status;

    public DeliveryDto() {}

    public DeliveryDto(Long id, String type, String status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}