package com.doncorleone.dondelivery.resources;

import java.net.URI;
import java.util.List;

import com.doncorleone.dondelivery.dto.OrderDTO;
import com.doncorleone.dondelivery.entities.Order;
import com.doncorleone.dondelivery.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestControllerAdvice
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;


    @GetMapping
    public ResponseEntity<OrderDTO> find(@PathVariable Long id){
        OrderDTO orderDTO = service.find(id);
        return ResponseEntity.ok().body(orderDTO);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order order){
        order = service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    /*
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}/delivered")
    public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id){
        OrderDTO dto = service.setDelivered(id);
        return ResponseEntity.ok().body(dto);

    }

     */
}