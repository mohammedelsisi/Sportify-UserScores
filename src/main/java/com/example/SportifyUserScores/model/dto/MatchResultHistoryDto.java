package com.example.SportifyUserScores.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class MatchResultHistoryDto
{
    private String userEmail;
    private long matchId;
    private long selectedTeamId;
    private long winningTeamId;

}
