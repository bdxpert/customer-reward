package com.example.assesment.repository;

import com.example.assesment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findAllByIdAndTransactionRecordsDateBetween(long customerId, LocalDate fromDate, LocalDate toDate);
    List<Customer> findAllByTransactionRecordsDateBetween(LocalDate fromDate, LocalDate toDate);
}
