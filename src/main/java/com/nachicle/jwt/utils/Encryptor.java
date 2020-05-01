package com.nachicle.jwt.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryptor {

    private Encryptor(){
    }

    public static String encrypt(String toEncrypt, EncryptionAlgorithm algorithm, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm.toString());
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
        mac.init(secretKey);
        byte[] hash = mac.doFinal(toEncrypt.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }

}
