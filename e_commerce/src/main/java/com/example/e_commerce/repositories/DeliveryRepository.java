package com.example.e_commerce.repositories;

import com.example.e_commerce.entities.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    //No se usa aun
}
