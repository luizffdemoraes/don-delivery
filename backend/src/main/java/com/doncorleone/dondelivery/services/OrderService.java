package com.doncorleone.dondelivery.services;

import com.doncorleone.dondelivery.dto.OrderDTO;
import com.doncorleone.dondelivery.entities.ItemOrder;
import com.doncorleone.dondelivery.entities.Order;
import com.doncorleone.dondelivery.entities.enums.OrderStatus;
import com.doncorleone.dondelivery.repositories.ItemOrderRepository;
import com.doncorleone.dondelivery.repositories.OrderRepository;
import com.doncorleone.dondelivery.repositories.ProductRepository;
import com.doncorleone.dondelivery.repositories.UserRepository;
import com.doncorleone.dondelivery.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public Order find(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
    }

    @Transactional
    public Order insert(Order order) {
        order.setId(null);
        order.setMoment(new Date());
        order.setUser(userRepository.findByEmail(order.getUser().getEmail()));
        order.setStatus(OrderStatus.PENDING);
        order = repository.save(order);

        for (ItemOrder itemOrder : order.getItens()) {
            itemOrder.setProduct(productService.find(itemOrder.getProduct().getId()));
            itemOrder.setPrice(itemOrder.getProduct().getPrice());
            itemOrder.setOrder(order);
        }

        itemOrderRepository.saveAll(order.getItens());
        return order;
    }

    @Transactional
    public Order setDelivered(Long id) {
        Order order = repository.getOne(id);
        order.setStatus(OrderStatus.DELIVRED);
        order = repository.save(order);
        return order;
    }

    @Transactional
    public Order setCanceled(Long id) {
        Order order = repository.getById(id);
        order.setStatus(OrderStatus.CANCELED);
        order = repository.save(order);
        return order;
    }

    /*
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


    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = repository.findOrderWithProducts();

        return list.stream()
                .map(x -> new OrderDTO(x))
                .collect(Collectors.toList());

    }






     */

}