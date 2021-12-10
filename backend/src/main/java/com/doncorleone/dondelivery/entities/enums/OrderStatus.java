package com.doncorleone.dondelivery.entities.enums;

public enum OrderStatus {

    PENDING(1),
    IN_PREPARATION(2),
    EN_ROUTE(3),
    DELIVRED(4),
    CANCELED(5);

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
