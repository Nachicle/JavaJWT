// TODO Finish javadocs.
package com.nachicle.jwt;

import com.nachicle.jwt.exceptions.InvalidTokenStringException;
import lombok.Getter;
import org.json.JSONObject;

import java.util.Base64;

/**
 * Exception thrown when an invalid token is found.
 *
 * @author Ignacio Blasco Muñoz
 * @author https://www.nachicle.com
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
public class JWToken {

    private String header;
    private String payload;
    private String signature;

    /**
     *
     * @param token
     * @throws InvalidTokenStringException
     */
    public JWToken(String token) throws InvalidTokenStringException {
        fromString(token);
    }

    /**
     *
     * @param token
     * @throws InvalidTokenStringException
     */
    public void fromString(String token) throws InvalidTokenStringException {
        if (token == null || token.isEmpty()) throw new InvalidTokenStringException("Null or empty string passed.");
        String[] parts = token.split("[.]");
        if (parts.length == 3) {
            header = parts[0];
            payload = parts[1];
            signature = parts[2];
        } else
            throw new InvalidTokenStringException("Invalid token syntax, it should be like header.payload.signature.");
    }

    /**
     *
     * @return
     */
    public JSONObject getDecodedHeader() {
        String decodedHeader = new String(Base64.getUrlDecoder().decode(header));
        return new JSONObject(decodedHeader);
    }

    /**
     *
     * @return
     */
    public JSONObject getDecodedPayload() {
        String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));
        return new JSONObject(decodedPayload);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return header + "." + payload + "." + signature;
    }

}
