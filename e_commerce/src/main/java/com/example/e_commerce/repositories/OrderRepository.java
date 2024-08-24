package com.example.e_commerce.repositories;

import com.example.e_commerce.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
