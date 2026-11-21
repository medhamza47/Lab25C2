package com.example.client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.cloud.consul.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:lab23",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa"
})
class ClientApplicationTests {

    @Test
    void contextLoads() {
    }

}
