package com.example.assesment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getTransactions() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/customers/transactions").toString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void getRewards() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/customers/rewards").toString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void getCustomerRewards() throws Exception {
        Long customerId = 2L;
        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/customers/rewards/"+customerId).toString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
