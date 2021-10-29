package com.doncorleone.dondelivery.entities.enums;

public enum PaymentStatus {

    PENDING(1),
    DEBIT(2),
    CREDIT(3),
    WHATSAPP(4),
    CANCELED(5);

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
