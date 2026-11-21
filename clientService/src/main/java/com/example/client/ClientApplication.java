package com.example.client;

import com.example.client.entities.Client;
import com.example.client.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    CommandLineRunner initializeClients(ClientRepository clientRepository) {
        return args -> {
            if (clientRepository.count() == 0) {
                clientRepository.saveAll(List.of(
                        new Client(null, "Amine SAFI", 23f),
                        new Client(null, "Amal ALAOUI", 22f),
                        new Client(null, "Samir RAMI", 22f)
                ));
            }
        };
    }
}
