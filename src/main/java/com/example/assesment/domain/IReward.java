package com.example.assesment.domain;

import com.example.assesment.entity.TransactionRecord;

public interface IReward {
    double calculatePoint(TransactionRecord transactionRecord);
}
