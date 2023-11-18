package com.example.assesment.domain;

import com.example.assesment.entity.TransactionRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RewardTest {

    @Autowired
    private IReward reward;

    @DisplayName("Test Customer reward calculation")
    @Test
    public void testGet() {
        TransactionRecord one = new TransactionRecord(2L, LocalDate.parse("2023-03-11"), 120.0);
        TransactionRecord two = new TransactionRecord(19L, LocalDate.parse("2023-03-11"), 40);
        TransactionRecord three = new TransactionRecord(20L, LocalDate.parse("2023-03-11"), 80.0);

        assertEquals(90, reward.calculatePoint(one));
        assertEquals(0, reward.calculatePoint(two));
        assertEquals(30, reward.calculatePoint(three));
    }

}
