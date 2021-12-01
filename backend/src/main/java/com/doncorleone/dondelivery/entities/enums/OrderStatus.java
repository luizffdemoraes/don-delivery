package com.doncorleone.dondelivery.entities.enums;

public enum OrderStatus {

    PENDING(1),
    EN_ROUTE(2),
    DELIVRED(3),
    CANCELED(4);

    private Integer cod;

    OrderStatus(Integer cod) {
        this.cod = cod;
    }

    public static OrderStatus toEnum(Integer cod ) {
        for( OrderStatus estado : OrderStatus.values() ) {
            if( estado.getCod().equals( cod ) )
                return estado;
        }

        throw new IllegalArgumentException( "Código inválido: " + cod );
    }

    public Integer getCod() {
        return cod;
    }
}
