package com.doncorleone.dondelivery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemOrderPK id = new ItemOrderPK();
    private Integer quantity;
    private Double price;

    public ItemOrder() {
    }

    public ItemOrder(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal() {
        return price * quantity;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public ItemOrderPK getId() {
        return id;
    }

    public void setId(ItemOrderPK id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrder)) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return Objects.equals(id, itemOrder.id) && Objects.equals(quantity, itemOrder.quantity) && Objects.equals(price, itemOrder.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price);
    }
}