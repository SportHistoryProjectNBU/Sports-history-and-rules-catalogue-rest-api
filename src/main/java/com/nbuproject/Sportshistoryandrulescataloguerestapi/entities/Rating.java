package com.nbuproject.Sportshistoryandrulescataloguerestapi.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by user on 1/21/2018.
 */
@Entity
public class Rating {
    private String id;
    private String matchId;
    private String userId;
    private Integer homeTeam;
    private Integer awayTeam;

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
    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
    @Column
    @NotNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Column
    public Integer getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Integer homeTeam) {
        this.homeTeam = homeTeam;
    }
    @Column
    public Integer getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Integer awayTeam) {
        this.awayTeam = awayTeam;
    }
}
