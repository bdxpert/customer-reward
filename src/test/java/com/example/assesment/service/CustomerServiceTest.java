package com.example.assesment.service;

import com.example.assesment.dto.CustomerRewardDTO;
import com.example.assesment.entity.Customer;
import com.example.assesment.entity.TransactionRecord;
import com.example.assesment.repository.CustomerRepository;
import com.example.assesment.repository.TransactionRecordRepository;
import com.example.assesment.util.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionRecordRepository transactionRecordRepository;
    private Customer customer;


    @BeforeEach
    public void setup() {
        TransactionRecord one = new TransactionRecord(200L, LocalDate.now(), 120.0);
        transactionRecordRepository.save(one);
        TransactionRecord two = new TransactionRecord(202L, LocalDate.now().minusMonths(1), 40);
        transactionRecordRepository.save(two);
        TransactionRecord three = new TransactionRecord(203L, LocalDate.now().minusMonths(2), 80.0);
        transactionRecordRepository.save(three);

        List<TransactionRecord> transactionRecordList = new ArrayList<>();
        transactionRecordList.add(one);
        transactionRecordList.add(two);
        transactionRecordList.add(three);

        customer = new Customer();
        customer.setId(7L);
        customer.setName("Customer 1");
        customer.setTransactionRecords(transactionRecordList);
    }

    @DisplayName("Test customer reward calculation")
    @Test
    void calculateReward() {
        Customer customer1 = customerRepository.save(customer);
        CustomerRewardDTO customerDTO = customerService.getCustomerReward(customer1.getId(), Utility.getSearchStartDate(), Utility.getSearchEndDate());

        assertEquals(120, customerDTO.getReward().getTotalRewardPoint());
        assertEquals(90, customerDTO.getReward().getFirstMonthRewardPoint());
        assertEquals(0, customerDTO.getReward().getSecondMonthRewardPoint());
        assertEquals(30, customerDTO.getReward().getThirdMonthRewardPoint());
    }

}
