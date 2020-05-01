package com.nachicle.jwt.utils;

public enum EncryptionAlgorithm {

    HS256("HmacSHA256");
    private final String text;

    EncryptionAlgorithm(final String text){
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }

}
