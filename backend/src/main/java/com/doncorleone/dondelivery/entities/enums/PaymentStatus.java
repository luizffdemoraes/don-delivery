package com.doncorleone.dondelivery.entities.enums;

public enum PaymentStatus {

    PENDING(1),
    DEBIT(2),
    CREDIT(3),
    FOOD_CARD(4),
    PIX(5),
    WHATSAPP(6),
    CANCELED(7),
    MONEY(8);

    private Integer cod;

    PaymentStatus(Integer cod) {
        this.cod = cod;
    }

    public static PaymentStatus toEnum(Integer cod ) {
        for( PaymentStatus estado : PaymentStatus.values() ) {
            if( estado.getCod().equals( cod ) )
                return estado;
        }

        throw new IllegalArgumentException( "Código inválido: " + cod );
    }

    public Integer getCod() {
        return cod;
    }
}

