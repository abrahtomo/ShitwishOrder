package com.codecool.shitwishorder;

import org.jose4j.jwk.HttpsJwks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public HttpsJwks getHttpsJwksBean(){
        return new HttpsJwks(System.getenv("JWKS_URI"));
    }
}
