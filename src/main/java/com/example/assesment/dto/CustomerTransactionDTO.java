package com.example.assesment.dto;

import com.example.assesment.entity.TransactionRecord;
import lombok.Data;

import java.util.List;

@Data
public class CustomerTransactionDTO {
    private long id;
    private String name;
    private List<TransactionRecord> transactionRecords;
}
