// TODO Finish javadocs.
package com.nachicle.jwt;

import com.nachicle.jwt.utils.HashAlgorithm;
import com.nachicle.jwt.utils.Hasher;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 * Class that generates a Json Web Token.
 *
 * @author Ignacio Blasco Muñoz
 * @author https://www.nachicle.com
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
public class JWTGenerator {

    private JSONObject header;
    private JSONObject payload;
    private String secret;
    private HashAlgorithm algorithm;

    /**
     *
     */
    public JWTGenerator() {
        header = new JSONObject();
        payload = new JSONObject();
        setTokenType("JWT");
        setAlgorithm(HashAlgorithm.HS256);
        setSecret("");
    }

    /**
     * @param typ
     * @return
     */
    public JWTGenerator setTokenType(String typ) {
        header.put("typ", typ);
        return this;
    }

    /**
     * @param alg
     * @return
     */
    public JWTGenerator setAlgorithm(HashAlgorithm alg) {
        algorithm = alg;
        header.put("alg", alg);
        return this;
    }

    /**
     * @param iss
     * @return
     */
    public JWTGenerator setIssuer(String iss) {
        payload.put("iss", iss);
        return this;
    }

    /**
     * @param sub
     * @return
     */
    public JWTGenerator setSubject(String sub) {
        payload.put("sub", sub);
        return this;
    }

    /**
     * @param aud
     * @return
     */
    public JWTGenerator setAudience(String aud) {
        payload.put("aud", aud);
        return this;
    }

    /**
     * @param exp
     * @return
     */
    public JWTGenerator setExpirationTime(LocalDateTime exp) {
        payload.put("exp", Timestamp.valueOf(exp).getTime() / 1000);
        return this;
    }

    /**
     * @param nbf
     * @return
     */
    public JWTGenerator setNotBefore(LocalDateTime nbf) {
        payload.put("nbf", Timestamp.valueOf(nbf).getTime() / 1000);
        return this;
    }

    /**
     * @param iat
     * @return
     */
    public JWTGenerator setIssuedAt(LocalDateTime iat) {
        payload.put("iat", Timestamp.valueOf(iat).getTime() / 1000);
        return this;
    }

    /**
     * @param jti
     * @return
     */
    public JWTGenerator setJWTID(String jti) {
        payload.put("jti", jti);
        return this;
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public JWTGenerator setClaim(String key, Object value) {
        payload.put(key, value);
        return this;
    }

    /**
     * @param secret
     * @return
     */
    public JWTGenerator setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    /**
     * @return
     */
    public JWToken generateToken() {
        JWToken token;
        try {
            String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.toString().getBytes());
            String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.toString().getBytes());
            String hashedSignature = Hasher.hash(encodedHeader + "." + encodedPayload, algorithm, secret);
            token = new JWToken(encodedHeader + "." + encodedPayload + "." + hashedSignature);
        } catch (Exception exc) {
            exc.printStackTrace();
            token = null;
        }
        return token;
    }

}
