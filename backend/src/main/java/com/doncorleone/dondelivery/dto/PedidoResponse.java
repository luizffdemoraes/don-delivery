package com.doncorleone.dondelivery.dto;

import com.doncorleone.dondelivery.domain.ItemPedido;
import com.doncorleone.dondelivery.entities.enums.OrderStatus;
import com.doncorleone.dondelivery.entities.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private Integer id;
    private Date instante;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private String email;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private String descricao;
    private Set<ItemPedido> itens;
}
