package com.nachicle.jwt;

import com.nachicle.jwt.utils.EncryptionAlgorithm;
import com.nachicle.jwt.utils.Encryptor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;

@Slf4j
@Getter
@Setter
public class JWTGenerator {

    JSONObject header;
    JSONObject payload;
    String secret;
    EncryptionAlgorithm algorithm;

    public JWTGenerator(){
        header = new JSONObject();
        payload = new JSONObject();
        setTokenType("JWT");
        setAlgorithm(EncryptionAlgorithm.HS256);
        setSecret("");
    }

    // Head setters
    public JWTGenerator setTokenType(String typ){
        header.put("typ", typ);
        return this;
    }
    public JWTGenerator setAlgorithm(EncryptionAlgorithm alg){
        algorithm = alg;
        header.put("alg", alg);
        return this;
    }

    // Payload setters
    public JWTGenerator setIssuer(String iss) {
        payload.put("iss", iss);
        return this;
    }
    public JWTGenerator setSubject(String sub) {
        payload.put("sub", sub);
        return this;
    }
    public JWTGenerator setAudience(String aud) {
        payload.put("aud", aud);
        return this;
    }
    public JWTGenerator setExpirationTime(LocalDateTime exp){
        payload.put("exp", Timestamp.valueOf(exp).getTime()/1000);
        return this;
    }
    public JWTGenerator setNotBefore(LocalDateTime nbf){
        payload.put("nbf", Timestamp.valueOf(nbf).getTime()/1000);
        return this;
    }
    public JWTGenerator setIssuedAt(LocalDateTime iat){
        payload.put("iat", Timestamp.valueOf(iat).getTime()/1000);
        return this;
    }
    public JWTGenerator setJWTID(String jti){
        payload.put("jti", jti);
        return this;
    }
    public JWTGenerator setClaim(String key, Object value) {
        payload.put(key, value);
        return this;
    }

    // Signature setters
    public JWTGenerator setSecret(String secret){
        this.secret = secret;
        return this;
    }

    public JWToken generateToken() {
        JWToken token;
        try {
            String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.toString().getBytes());
            String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.toString().getBytes());
            String hashedSignature = Encryptor.encrypt(encodedHeader + "." + encodedPayload, algorithm, secret);
            token = new JWToken(encodedHeader + "." + encodedPayload + "." + hashedSignature);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            token = null;
        }
        return token;
    }

}
