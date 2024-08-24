package com.example.e_commerce.controller;

import com.example.e_commerce.dto.DeliveryDto;
import com.example.e_commerce.entities.Delivery;
import com.example.e_commerce.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @GetMapping
    public List<DeliveryDto> getAllDeliveries() {
        return ((List<Delivery>) deliveryRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DeliveryDto createDelivery(@RequestBody DeliveryDto deliveryDto) {
        Delivery delivery = convertToEntity(deliveryDto);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return convertToDto(savedDelivery);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryDto> updateDelivery(@PathVariable Long id, @RequestBody DeliveryDto deliveryDto) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isPresent()) {
            Delivery updatedDelivery = delivery.get();
            updatedDelivery.setType(deliveryDto.getType());
            updatedDelivery.setStatus(deliveryDto.getStatus());
            return ResponseEntity.ok(convertToDto(deliveryRepository.save(updatedDelivery)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isPresent()) {
            deliveryRepository.delete(delivery.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private DeliveryDto convertToDto(Delivery delivery) {
        return new DeliveryDto(delivery.getId(), delivery.getType(), delivery.getStatus());
    }

    private Delivery convertToEntity(DeliveryDto deliveryDto) {
        Delivery delivery = new Delivery();
        delivery.setId(deliveryDto.getId());
        delivery.setType(deliveryDto.getType());
        delivery.setStatus(deliveryDto.getStatus());
        return delivery;
    }
}