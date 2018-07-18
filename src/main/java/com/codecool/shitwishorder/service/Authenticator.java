package com.codecool.shitwishorder.service;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Authenticator {

    private HttpsJwksVerificationKeyResolver resolver;

    public Authenticator(HttpsJwks httpsJwks) {
        this.resolver = new HttpsJwksVerificationKeyResolver(httpsJwks);
    }


    public String getTokenString(String token) {
        String ret = "";
        try {
            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setExpectedIssuer(System.getenv("ISSUER"))
                    .setExpectedAudience(System.getenv("AUDIENCE"))
                    .setVerificationKeyResolver(resolver)
                    .setJweAlgorithmConstraints(
                            new AlgorithmConstraints(
                                    AlgorithmConstraints.ConstraintType.WHITELIST,
                                    AlgorithmIdentifiers.RSA_USING_SHA256
                            )
                    )
                    .build();
            JwtClaims claims = jwtConsumer.processToClaims(token);
            if (claims.getExpirationTime().getValueInMillis() < new Date().getTime()) {
                System.out.println("Token has already expired!");
                ret = "Token Expired";
            } else {
                ret = claims.getSubject();
            }
        } catch (InvalidJwtException | MalformedClaimException e) {
            System.out.println("Invalid token!\n" + e);
            ret = "Invalid Token";
        }

        return ret;
    }
}

