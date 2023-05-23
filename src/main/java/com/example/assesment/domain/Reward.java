package com.example.assesment.domain;

import com.example.assesment.entity.TransactionRecord;
import org.springframework.stereotype.Component;

@Component
public class Reward implements IReward {
    public double calculatePoint(TransactionRecord transactionRecord) {

        double pointsForMoreThanHundred = (transactionRecord.getAmount() - 100) > 0 ?
                (transactionRecord.getAmount() - 100) * 2 : 0;

        double pointForMoreThanOrEqualToFiftyAndLessThanOrEqualToHundred =
                (transactionRecord.getAmount() - 100) > 0 ?
                        50 : transactionRecord.getAmount() > 50 ?
                        transactionRecord.getAmount() - 50 : 0;

        return pointForMoreThanOrEqualToFiftyAndLessThanOrEqualToHundred + pointsForMoreThanHundred;
    }
}
