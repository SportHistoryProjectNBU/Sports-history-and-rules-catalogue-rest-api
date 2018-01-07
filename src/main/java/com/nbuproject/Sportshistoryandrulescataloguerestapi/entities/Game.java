package com.nbuproject.Sportshistoryandrulescataloguerestapi.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by user on 10/28/2017.
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"date", "homeTeamName", "awayTeamName"})
})
public class Game {
    private String id;
    private String date;
    private String status;
    private Integer matchday;
    private String homeTeamName;
    private String awayTeamName;
    private Integer goalsHomeTeamFirstHalf;
    private Integer goalsAwayTeamFirstHalf;
    private Integer goalsHomeTeamSecondHalf;
    private Integer goalsAwayTeamSecondHalf;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    @NotNull
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column
    @NotNull
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column
    @NotNull
    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    @Column
    @NotNull
    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    @Column
    @NotNull
    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    @Column
    public Integer getGoalsHomeTeamFirstHalf() {
        return goalsHomeTeamFirstHalf;
    }

    public void setGoalsHomeTeamFirstHalf(Integer goalsHomeTeamFirstHalf) {
        this.goalsHomeTeamFirstHalf = goalsHomeTeamFirstHalf;
    }

    @Column
    public Integer getGoalsAwayTeamFirstHalf() {
        return goalsAwayTeamFirstHalf;
    }

    public void setGoalsAwayTeamFirstHalf(Integer goalsAwayTeamFirstHalf) {
        this.goalsAwayTeamFirstHalf = goalsAwayTeamFirstHalf;
    }

    @Column
    public Integer getGoalsHomeTeamSecondHalf() {
        return goalsHomeTeamSecondHalf;
    }

    public void setGoalsHomeTeamSecondHalf(Integer goalsHomeTeamSecondHalf) {
        this.goalsHomeTeamSecondHalf = goalsHomeTeamSecondHalf;
    }

    @Column
    public Integer getGoalsAwayTeamSecondHalf() {
        return goalsAwayTeamSecondHalf;
    }

    public void setGoalsAwayTeamSecondHalf(Integer goalsAwayTeamSecondHalf) {
        this.goalsAwayTeamSecondHalf = goalsAwayTeamSecondHalf;
    }

}
