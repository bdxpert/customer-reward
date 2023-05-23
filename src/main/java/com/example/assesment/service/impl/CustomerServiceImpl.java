package com.example.assesment.service.impl;

import com.example.assesment.entity.Customer;
import com.example.assesment.domain.IReward;
import com.example.assesment.entity.TransactionRecord;
import com.example.assesment.dto.CustomerRewardDTO;
import com.example.assesment.dto.CustomerTransactionDTO;
import com.example.assesment.dto.RewardDTO;
import com.example.assesment.exception.APIRequestException;
import com.example.assesment.repository.CustomerRepository;
import com.example.assesment.service.CustomerService;

import com.example.assesment.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private IReward reward;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, IReward reward) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.reward = reward;
    }

    public CustomerRewardDTO getCustomerReward(long customerId, LocalDate startDate, LocalDate endDate) {

        Optional<Customer> customer =
                customerRepository.findAllByIdAndTransactionRecordsDateBetween(customerId, startDate, endDate);

        if (!customer.isPresent()) throw new APIRequestException("Customer doesn't exists.", HttpStatus.BAD_REQUEST);
        log.info("Calculate reward points.");
        RewardDTO reward = calculateReward(customer.get(), Utility.getSearchStartDate(), Utility.getSearchEndDate());
        customer.get().setReward(reward);
        CustomerRewardDTO customerRewardDTO = modelMapper.map(customer.get(), CustomerRewardDTO.class);

        return customerRewardDTO;
    }

    public List<CustomerRewardDTO> getAllCustomerReward(LocalDate startDate, LocalDate endDate) {
        log.info("Calculate reward points for all customer...");
        List<Customer> customers = customerRepository.findAllByTransactionRecordsDateBetween(startDate, endDate);

        for (Customer customer : customers) {
            RewardDTO reward = calculateReward(customer, startDate, endDate);
            customer.setReward(reward);
        }

        List<CustomerRewardDTO> customerRewardDTOs = customers
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerRewardDTO.class))
                .collect(Collectors.toList());

        return customerRewardDTOs;
    }

    public List<CustomerTransactionDTO> getCustomersTransactions() {
        log.info("Fetching Customer Transactions Started...");
        List<Customer> customers = customerRepository.findAll();

        List<CustomerTransactionDTO> customerTransactionDTOs = customers
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerTransactionDTO.class))
                .collect(Collectors.toList());

        log.info("Fetching Customer Transactions ends...");
        return customerTransactionDTOs;
    }

    private RewardDTO calculateReward(Customer customer, LocalDate startDate, LocalDate endDate) {
        //Extract all the dates of transactions

        LocalDate fistMonthStartDate = Utility.getFirstMonthStartDate(startDate);
        LocalDate secondMonthStartDate = Utility.getSecondMonthStartDate(endDate);
        LocalDate lastMonthStartDate = Utility.getLastMonthStartDate(endDate);

        double firstMonthRewardPoint = 0.0;
        double secondMonthRewardPoint = 0.0;
        double thirdMonthRewardPoint = 0.0;

        for (TransactionRecord transactionRecord : customer.getTransactionRecords()) {
            if (Utility.matchMonthOnStartAndEndDate(fistMonthStartDate, transactionRecord.getDate())) {
                firstMonthRewardPoint += reward.calculatePoint(transactionRecord);
            } else if (Utility.matchMonthOnStartAndEndDate(secondMonthStartDate, transactionRecord.getDate())) {
                secondMonthRewardPoint += reward.calculatePoint(transactionRecord);
            } else if (Utility.matchMonthOnStartAndEndDate(lastMonthStartDate, transactionRecord.getDate())) {
                thirdMonthRewardPoint += reward.calculatePoint(transactionRecord);
            }
        }

        return getReward(firstMonthRewardPoint, secondMonthRewardPoint, thirdMonthRewardPoint);
    }
    private RewardDTO getReward(double firstMonthRewardPoint,double secondMonthRewardPoint, double thirdMonthRewardPoint)
    {
        double totalReward = firstMonthRewardPoint + secondMonthRewardPoint + thirdMonthRewardPoint;
        return new RewardDTO(firstMonthRewardPoint,secondMonthRewardPoint, thirdMonthRewardPoint, totalReward);
    }
}