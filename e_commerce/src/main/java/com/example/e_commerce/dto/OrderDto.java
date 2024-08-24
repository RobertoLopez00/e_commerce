package com.example.e_commerce.dto;

public class OrderDto {
    private Long id;
    private String orderDate;
    private CustomerDto customer;
    private DeliveryDto delivery;

    public OrderDto() {}

    public OrderDto(Long id, String orderDate, CustomerDto customer, DeliveryDto delivery) {
        this.id = id;
        this.orderDate = orderDate;
        this.customer = customer;
        this.delivery = delivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public DeliveryDto getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryDto delivery) {
        this.delivery = delivery;
    }
}