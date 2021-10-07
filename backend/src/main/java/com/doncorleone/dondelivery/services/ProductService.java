package com.doncorleone.dondelivery.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.doncorleone.dondelivery.dto.ProductDTO;
import com.doncorleone.dondelivery.entities.Product;
import com.doncorleone.dondelivery.repositories.ProductRepository;
import com.doncorleone.dondelivery.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product find(Long id) {
        Optional<Product> obj = repository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAllByOrderByNameAsc();

        return list.stream()
                .map(x -> new ProductDTO(x))

                .collect(Collectors.toList());

    }
}