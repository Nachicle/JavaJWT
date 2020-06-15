package com.nachicle.jwt.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Class that helps to hash an string, using given algorithm.
 *
 * @author Ignacio Blasco Muñoz
 * @author https://www.nachicle.com
 * @version 0.0.1
 * @since 0.0.1
 */

public class Hasher {

    /**
     * Avoids hasher instancing.
     */
    private Hasher() {
    }

    /**
     * Method that hashes a string with a given hashing algorithm.
     *
     * @param toHash    String to hash.
     * @param algorithm Hashing algorithm to use.
     * @param secret    Secret used for hashing.
     * @return hash Hashed string.
     * @throws NoSuchAlgorithmException Exception thrown when an invalid algorithm is given.
     * @throws InvalidKeyException      Exception when invalid string is given to hash.
     */
    public static String hash(String toHash, HashAlgorithm algorithm, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm.toString());
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
        mac.init(secretKey);
        byte[] hash = mac.doFinal(toHash.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }

}
