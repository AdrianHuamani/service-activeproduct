package com.bootcamp.activeProduct.common;

public enum ErrorMessage {
    CLIENT_NOT_FOUND("El clienteno existe"),
    LOAN_RESTRICTION("Ya tiene con otro préstamo personal en curso"),
    CREDITCARD_NOT_FOUND("La TC no existe"),
    LIMIT_CREDITCARD("El monto supera el límite de crédito");

    private String value;
    ErrorMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
