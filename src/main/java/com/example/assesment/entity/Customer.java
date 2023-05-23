package com.example.assesment.entity;

import com.example.assesment.dto.RewardDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany
    @JoinColumn(name="customer_id")
    private List<TransactionRecord> transactionRecords;
    @Transient
    private RewardDTO reward;
}
