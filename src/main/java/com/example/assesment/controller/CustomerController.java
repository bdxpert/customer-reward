package com.example.assesment.controller;

import com.example.assesment.dto.CustomerRewardDTO;
import com.example.assesment.dto.CustomerTransactionDTO;
import com.example.assesment.exception.APIRequestException;
import com.example.assesment.service.CustomerService;
import com.example.assesment.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/rewards")
    public ResponseEntity<?> getRewards() {
        try {
            List<CustomerRewardDTO> customerRewardDTO = customerService.getAllCustomerReward(Utility.getSearchStartDate(), Utility.getSearchEndDate());
            log.info("Customer rewards fetch success");
            return new ResponseEntity<List<CustomerRewardDTO>>(customerRewardDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new APIRequestException("Unable to fetch customer reward" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<?> getRewardByCustomerId(@PathVariable long customerId) {
        try {
            CustomerRewardDTO customerRewardDTO = customerService.getCustomerReward(customerId, Utility.getSearchStartDate(), Utility.getSearchEndDate());
            log.info("Customer rewards fetch success of ID:{}", customerId);
            return new ResponseEntity<CustomerRewardDTO>(customerRewardDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new APIRequestException("Unable to fetch customer reward", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions() {
        try {
            List<CustomerTransactionDTO> customerTransactionDTOs = customerService.getCustomersTransactions();
            log.info("Customer transaction fetch success");
            return new ResponseEntity<List<CustomerTransactionDTO>>(customerTransactionDTOs, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new APIRequestException("Unable to fetch transactions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
