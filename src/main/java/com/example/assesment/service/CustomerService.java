package com.example.assesment.service;

import com.example.assesment.dto.CustomerRewardDTO;
import com.example.assesment.dto.CustomerTransactionDTO;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    public List<CustomerTransactionDTO> getCustomersTransactions();
    public CustomerRewardDTO getCustomerReward(long CustomerId, LocalDate startDate, LocalDate endDate);
    public List<CustomerRewardDTO> getAllCustomerReward(LocalDate startDate, LocalDate endDate);
}
