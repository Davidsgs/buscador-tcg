package com.buscadorcriollo.mtg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BuscadorCriolloMtgApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuscadorCriolloMtgApplication.class, args);
    }

}
