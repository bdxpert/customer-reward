package com.example.assesment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionRecord {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate date;
    private double amount;

}
