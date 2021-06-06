package com.example.SportifyUserScores.model.orm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MatchSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long matchId;
    private long selectedTeamId;
    private String userEmail;
}
