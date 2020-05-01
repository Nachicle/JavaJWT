package com.nachicle.jwt;

import com.nachicle.jwt.utils.EncryptionAlgorithm;
import com.nachicle.jwt.utils.Encryptor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class JWTValidator {

    String secret;
    EncryptionAlgorithm algorithm;

    // Signature setters
    public JWTValidator setAlgorithm(EncryptionAlgorithm alg){
        algorithm = alg;
        return this;
    }
    public JWTValidator setSecret(String scrt){
        secret = scrt;
        return this;
    }

    public Boolean validate (JWToken token) {
        try {
            String hashedSignature = Encryptor.encrypt(token.getHeader() + "." + token.getPayload(), algorithm, secret);
            return token.getSignature().equals(hashedSignature);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

}
