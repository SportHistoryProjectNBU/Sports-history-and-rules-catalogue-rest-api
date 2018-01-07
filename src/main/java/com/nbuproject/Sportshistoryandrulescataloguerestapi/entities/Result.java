package com.nbuproject.Sportshistoryandrulescataloguerestapi.entities;

/**
 * Created by user on 1/7/2018.
 */
public class Result {
    private Integer goalsHomeTeam;
    private Integer goalsAwayTeam;
    private HalfTimeResult halfTime;

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(Integer goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public HalfTimeResult getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTimeResult halfTime) {
        this.halfTime = halfTime;
    }
}
