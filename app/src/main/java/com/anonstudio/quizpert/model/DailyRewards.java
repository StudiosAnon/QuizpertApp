package com.anonstudio.quizpert.model;

public class DailyRewards {
    int dailyRewardSize = 1;
    String gems = "Gems";
    String explosionPowerUp = "exPowerUp";
    String dicePowerUp = "dicePowerup";
    String dailyReward = "";



    public void checkDailyReward() {
        final int dailyRewardRange = (3 - 0) + 1;
        int dailyRewardNumber = (int) (Math.random() * dailyRewardRange);

        switch(dailyRewardNumber) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }


    public String getDailyReward() {
        return dailyReward;
    }
}
