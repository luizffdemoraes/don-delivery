package com.doncorleone.dondelivery.dto;

import com.doncorleone.dondelivery.entities.ItemOrder;
import com.doncorleone.dondelivery.entities.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private String client;

    private String email;

    private String address;

    private Double latitude;

    private Double longitude;

    private Date moment;

    private OrderStatus status;

    private Double total;

    private List<Double> itens;


}
