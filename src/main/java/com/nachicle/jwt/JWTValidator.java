// TODO Finish javadocs.
package com.nachicle.jwt;

import com.nachicle.jwt.utils.HashAlgorithm;
import com.nachicle.jwt.utils.Hasher;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Exception thrown when an invalid token is found.
 *
 * @author Ignacio Blasco Muñoz
 * @author https://www.nachicle.com
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
public class JWTValidator {

    private String secret;
    private HashAlgorithm algorithm;

    /**
     *
      * @param alg
     * @return
     */
    public JWTValidator setAlgorithm(HashAlgorithm alg) {
        algorithm = alg;
        return this;
    }

    /**
     *
     * @param scrt
     * @return
     */
    public JWTValidator setSecret(String scrt) {
        secret = scrt;
        return this;
    }

    /**
     *
     * @param token
     * @return
     */
    public Boolean validate(JWToken token) {
        try {
            String hashedSignature = Hasher.hash(token.getHeader() + "." + token.getPayload(), algorithm, secret);
            return token.getSignature().equals(hashedSignature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
