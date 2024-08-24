package com.example.e_commerce.repositories;

import com.example.e_commerce.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
