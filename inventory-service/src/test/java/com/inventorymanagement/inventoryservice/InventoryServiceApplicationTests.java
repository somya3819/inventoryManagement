package com.inventorymanagement.inventoryservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class InventoryServiceApplicationTests {

    @Test
    void contextLoads() {
        // verifies the Spring context loads with the test profile using H2
    }
}
