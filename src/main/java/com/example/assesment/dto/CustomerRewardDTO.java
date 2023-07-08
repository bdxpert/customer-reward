package com.example.assesment.dto;

import com.example.assesment.domain.Reward;
import lombok.Data;

@Data
public class CustomerRewardDTO {
    private long id;
    private String name;
    private RewardDTO reward;
}
