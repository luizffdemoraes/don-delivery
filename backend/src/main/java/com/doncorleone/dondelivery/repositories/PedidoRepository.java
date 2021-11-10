package com.doncorleone.dondelivery.repositories;


import com.doncorleone.dondelivery.domain.Pedido;
import com.doncorleone.dondelivery.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly=true)
    Page<Pedido> findByUser(User user, Pageable pageRequest);
}
