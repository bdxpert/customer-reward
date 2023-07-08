package com.example.assesment.service;

import com.example.assesment.domain.IReward;
import com.example.assesment.domain.Reward;
import com.example.assesment.dto.CustomerRewardDTO;
import com.example.assesment.entity.Customer;
import com.example.assesment.entity.TransactionRecord;
import com.example.assesment.repository.CustomerRepository;
import com.example.assesment.service.impl.CustomerServiceImpl;
import com.example.assesment.util.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/reference/htmlsingle/#boot-features-testing-spring-boot-applications
@SpringBootTest
public class CustomerServiceTest {


    CustomerService customerService;
    ModelMapper modelMapper;
    CustomerRepository customerRepository;
    IReward iReward;

    private Customer customer;


    @BeforeEach
    public void setup() {
        iReward = new Reward();
        customerRepository = mock(CustomerRepository.class);
        modelMapper = new ModelMapper();
        customerService = new CustomerServiceImpl(customerRepository, modelMapper, iReward);

        TransactionRecord one = new TransactionRecord(200L, LocalDate.now(), 120.0);
        TransactionRecord two = new TransactionRecord(202L, LocalDate.now().minusMonths(1), 40);
        TransactionRecord three = new TransactionRecord(203L, LocalDate.now().minusMonths(2), 80.0);

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
        when(customerRepository.findAllByIdAndTransactionRecordsDateBetween(customer.getId(), Utility.getSearchStartDate(), Utility.getSearchEndDate())).thenAnswer(ans->{
            return Optional.of(customer);
        });

        CustomerRewardDTO customerDTO = customerService.getCustomerReward(customer.getId(), Utility.getSearchStartDate(), Utility.getSearchEndDate());

        assertEquals(120, customerDTO.getReward().getTotalRewardPoint());
        assertEquals(30, customerDTO.getReward().getFirstMonthRewardPoint());
        assertEquals(0, customerDTO.getReward().getSecondMonthRewardPoint());
        assertEquals(90, customerDTO.getReward().getThirdMonthRewardPoint());
    }

}
