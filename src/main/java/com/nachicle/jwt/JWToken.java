package com.nachicle.jwt;

import com.nachicle.jwt.exceptions.InvalidTokenStringException;
import lombok.Getter;
import org.json.JSONObject;

import java.util.Base64;

@Getter
public class JWToken {

    private String header;
    private String payload;
    private String signature;

    public JWToken(String token) throws InvalidTokenStringException{
        fromString(token);
    }
    public void fromString(String token) throws InvalidTokenStringException {
        if(token == null || token.isEmpty()) throw new InvalidTokenStringException("Null or empty string passed.");
        String[] parts = token.split("[.]");
        if(parts.length == 3) {
            header = parts[0];
            payload = parts[1];
            signature = parts[2];
        } else throw new InvalidTokenStringException("Invalid token syntax, it should be like header.payload.signature.");
    }

    public JSONObject getDecodedHeader() {
        String decodedHeader = new String(Base64.getUrlDecoder().decode(header));
        return new JSONObject(decodedHeader);
    }
    public JSONObject getDecodedPayload() {
        String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));
        return new JSONObject(decodedPayload);
    }

    @Override
    public String toString() {
        return header + "." + payload + "." + signature;
    }

}
