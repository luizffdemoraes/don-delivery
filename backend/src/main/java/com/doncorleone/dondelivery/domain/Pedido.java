package com.doncorleone.dondelivery.domain;

import com.doncorleone.dondelivery.entities.User;
import com.doncorleone.dondelivery.entities.enums.OrderStatus;
import com.doncorleone.dondelivery.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instante;

    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String endereco;
    private Double latitude;
    private Double longitude;
    private String descricao;

    @OneToMany(mappedBy="id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Integer id, User user, String endereco, Double latitude, Double longitude, Date instante, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.instante = instante;
        this.status = status;
    }

    public Pedido(Integer id, User user, String endereco, Double latitude, Double longitude, Date instante, OrderStatus status, String descricao) {
        this.id = id;
        this.user = user;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.instante = instante;
        this.status = status;
        this.descricao = descricao;
    }

    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido ip : itens) {
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido n??mero: ");
        builder.append(getId());
        builder.append(", Instante: ");
        builder.append(sdf.format(getInstante()));
        builder.append(", Cliente: ");
        builder.append(getUser().getEmail());
        builder.append(", Situa????o do pagamento: ");
        builder.append(getPaymentStatus());
        builder.append("\nDetalhes:\n");
        for (ItemPedido ip : getItens()) {
            builder.append(ip.toString());
        }
        builder.append("Valor total: ");
        builder.append(nf.format(getValorTotal()));
        return builder.toString();
    }
}
