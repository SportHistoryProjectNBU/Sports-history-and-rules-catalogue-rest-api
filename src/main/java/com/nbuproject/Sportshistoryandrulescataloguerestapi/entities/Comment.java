package com.nbuproject.Sportshistoryandrulescataloguerestapi.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by user on 1/21/2018.
 */
@Entity
public class Comment {
    private String id;
    private String userName;
    private String matchId;
    private Timestamp date;
    private String comment;


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
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
