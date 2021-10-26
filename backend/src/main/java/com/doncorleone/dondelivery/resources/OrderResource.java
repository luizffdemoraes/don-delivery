package com.doncorleone.dondelivery.resources;

import java.net.URI;
import java.util.List;

import com.doncorleone.dondelivery.dto.OrderDTO;
import com.doncorleone.dondelivery.entities.Order;
import com.doncorleone.dondelivery.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @RequestMapping(value="/{id}")
    public ResponseEntity<Order> find(@PathVariable Long id) {
        Order obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<Order>> findAll(Pageable pageable) {

        Page<Order> list = service.findAllPaged(pageable);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order order){
        order = service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(order);
    }

    @PutMapping("/{id}/delivered")
    public ResponseEntity<Order> setDelivered(@PathVariable Long id){
        Order dto = service.setDelivered(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/canceled")
    public ResponseEntity<Order> setCanceled(@PathVariable Long id){
        Order dto = service.setCanceled(id);
        return ResponseEntity.ok().body(dto);

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



     */
}