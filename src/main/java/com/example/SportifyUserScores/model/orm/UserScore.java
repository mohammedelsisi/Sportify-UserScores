package com.example.SportifyUserScores.model.orm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserScore {
    @Id
    private String userEmail;

    private int score;
}
