package com.example.SportifyUserScores.model.dto;

import lombok.Data;
import lombok.Value;

@Data
public class MatchSelectionDto {
    private String userEmail;
    private long matchId;
    private long selectedTeamId;

}
