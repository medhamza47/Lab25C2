package com.fateway.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.cloud.consul.enabled=false"
})
class GatewayApplicationTests {

    @Test
    void contextLoads() {
    }

}
