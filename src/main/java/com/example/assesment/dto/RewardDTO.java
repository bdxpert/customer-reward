package com.example.assesment.dto;

import lombok.Data;

@Data
public class RewardDTO {
    private double firstMonthRewardPoint;
    private double secondMonthRewardPoint;
    private double thirdMonthRewardPoint;
    private double totalRewardPoint;

    public RewardDTO(double firstMonthRewardPoint, double secondMonthRewardPoint, double thirdMonthRewardPoint, double totalRewardPoint) {
        this.firstMonthRewardPoint = firstMonthRewardPoint;
        this.secondMonthRewardPoint = secondMonthRewardPoint;
        this.thirdMonthRewardPoint = thirdMonthRewardPoint;
        this.totalRewardPoint = totalRewardPoint;
    }
}
