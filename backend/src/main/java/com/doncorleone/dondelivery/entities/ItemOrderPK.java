package com.doncorleone.dondelivery.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemOrderPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Order order;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrderPK)) return false;
        ItemOrderPK that = (ItemOrderPK) o;
        return product.equals(that.product) && order.equals(that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order);
    }
}