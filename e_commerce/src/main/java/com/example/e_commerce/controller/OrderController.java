// src/main/java/com/example/e_commerce/controller/OrderController.java
package com.example.e_commerce.controller;

import com.example.e_commerce.dto.OrderDto;
import com.example.e_commerce.dto.CustomerDto;
import com.example.e_commerce.dto.DeliveryDto;
import com.example.e_commerce.entities.Order;
import com.example.e_commerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        return orders.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setOrderDate(order.getOrderDate().toString());

            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(order.getCustomer().getId());
            customerDto.setName(order.getCustomer().getName());
            customerDto.setEmail(order.getCustomer().getEmail());
            customerDto.setAddress(order.getCustomer().getAddress());
            orderDto.setCustomer(customerDto);

            DeliveryDto deliveryDto = new DeliveryDto();
            deliveryDto.setId(order.getDelivery().getId());
            deliveryDto.setType(order.getDelivery().getType());
            deliveryDto.setStatus(order.getDelivery().getStatus());
            orderDto.setDelivery(deliveryDto);

            return orderDto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.get().getId());
            orderDto.setOrderDate(order.get().getOrderDate().toString());

            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(order.get().getCustomer().getId());
            customerDto.setName(order.get().getCustomer().getName());
            customerDto.setEmail(order.get().getCustomer().getEmail());
            customerDto.setAddress(order.get().getCustomer().getAddress());
            orderDto.setCustomer(customerDto);

            DeliveryDto deliveryDto = new DeliveryDto();
            deliveryDto.setId(order.get().getDelivery().getId());
            deliveryDto.setType(order.get().getDelivery().getType());
            deliveryDto.setStatus(order.get().getDelivery().getStatus());
            orderDto.setDelivery(deliveryDto);

            return ResponseEntity.ok(orderDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody Order order) {
        Order newOrder = orderRepository.save(order);
        OrderDto orderDto = new OrderDto();
        orderDto.setId(newOrder.getId());
        orderDto.setOrderDate(newOrder.getOrderDate().toString());

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(newOrder.getCustomer().getId());
        customerDto.setName(newOrder.getCustomer().getName());
        customerDto.setEmail(newOrder.getCustomer().getEmail());
        customerDto.setAddress(newOrder.getCustomer().getAddress());
        orderDto.setCustomer(customerDto);

        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(newOrder.getDelivery().getId());
        deliveryDto.setType(newOrder.getDelivery().getType());
        deliveryDto.setStatus(newOrder.getDelivery().getStatus());
        orderDto.setDelivery(deliveryDto);

        return orderDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order updatedOrder = order.get();
            updatedOrder.setOrderDate(orderDetails.getOrderDate());
            updatedOrder.setCustomer(orderDetails.getCustomer());
            updatedOrder.setDelivery(orderDetails.getDelivery());
            return ResponseEntity.ok(orderRepository.save(updatedOrder));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}