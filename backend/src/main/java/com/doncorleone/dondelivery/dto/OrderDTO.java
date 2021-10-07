package com.doncorleone.dondelivery.dto;

import com.doncorleone.dondelivery.entities.Order;
import com.doncorleone.dondelivery.entities.enums.OrderStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String address;

    private Double latitude;

    private Double longitude;

    private Date moment;

    private OrderStatus status;

    private Double total;

    private List<ProductDTO> products = new ArrayList<>();


    public OrderDTO(Long id, String address, Double latitude, Double longitude, Date moment, OrderStatus status, Double total) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.moment = moment;
        this.status = status;
        this.total = total;
    }


    public OrderDTO(Order entity) {
        id = entity.getId();
        address = entity.getAddress();
        latitude = entity.getLatitude();
        longitude = entity.getLongitude();
        moment = entity.getMoment();
        status = entity.getStatus();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
}