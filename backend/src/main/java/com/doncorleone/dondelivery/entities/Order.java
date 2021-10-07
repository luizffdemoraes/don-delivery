package com.doncorleone.dondelivery.entities;

import com.doncorleone.dondelivery.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date moment;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id.order")
    private transient Set<ItemOrder> itens = new HashSet<>();

    @Deprecated
    public Order() {
    }

    public Order(Long id, User user, String address, Double latitude, Double longitude, Date moment, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.moment = moment;
        this.status = status;
    }

    public double getAmount(){
        double soma = 0.0;
        for (ItemOrder io : itens){
            soma = soma + io.getSubTotal();
        }
        return soma;
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

    public Set<ItemOrder> getItens() {
        return itens;
    }

    public void setItens(Set<ItemOrder> itens) {
        this.itens = itens;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id.equals(order.id) && address.equals(order.address) && latitude.equals(order.latitude) && longitude.equals(order.longitude) && moment.equals(order.moment) && status == order.status && itens.equals(order.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, latitude, longitude, moment, status, itens);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance( new Locale("pt", "BR") );
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        StringBuilder builder = new StringBuilder();
        builder.append("Pedido número: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getMoment()));
        builder.append(", Cliente: ");
        builder.append(getUser().getEmail());
        builder.append(", Situação do pagamento: ");
        builder.append("\nDetalhes:\n");

        for (ItemOrder itemPedido : itens) {
            builder.append(itemPedido.toString());
        }

        builder.append("Valor total: ");
        builder.append(nf.format(getAmount()));
        builder.append("\n");

        return builder.toString();
    }
}