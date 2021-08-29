package com.doncorleone.dondelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.doncorleone.dondelivery.dto.OrderDTO;
import com.doncorleone.dondelivery.dto.ProductDTO;
import com.doncorleone.dondelivery.entities.Order;
import com.doncorleone.dondelivery.entities.OrderStatus;
import com.doncorleone.dondelivery.entities.Product;
import com.doncorleone.dondelivery.repositories.OrderRepository;
import com.doncorleone.dondelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = repository.findOrderWithProducts();
        return list.stream()
                .map(x -> new OrderDTO(x))
                .collect(Collectors.toList());

    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
                Instant.now(), OrderStatus.PENDING);
        for (ProductDTO p : dto.getProducts()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = repository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id) {
        Order order = repository.getOne(id);
        order.setStatus(OrderStatus.DELIVRED);
        order = repository.save(order);
        return new OrderDTO(order);


    }

}