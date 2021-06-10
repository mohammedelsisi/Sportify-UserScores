package com.example.SportifyUserScores.model.dto.broker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResultMsgDto {
    private long matchId;
    private long winningTeamId;
}
