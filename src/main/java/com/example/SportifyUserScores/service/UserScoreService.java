package com.example.SportifyUserScores.service;

import com.example.SportifyUserScores.model.dto.broker.MatchResultMsgDto;
import com.example.SportifyUserScores.repo.MatchSelectionJpaRepo;
import com.example.SportifyUserScores.repo.UserScoreJpaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserScoreService {
    private final UserScoreJpaRepo userScoreJpaRepo;
    private final MatchSelectionJpaRepo matchSelectionJpaRepo;

    public UserScoreService(UserScoreJpaRepo userScoreJpaRepo, MatchSelectionJpaRepo matchSelectionJpaRepo) {
        this.userScoreJpaRepo = userScoreJpaRepo;
        this.matchSelectionJpaRepo = matchSelectionJpaRepo;
    }

    // todo users must already exist in the db
    public void updateUserScoreByMatch(MatchResultMsgDto matchResultMsgDto) {
        // find winning user emails using match id
        List<String> userEmails = matchSelectionJpaRepo.queryUserEmailByMatchIdAndSelectedTeamId(matchResultMsgDto.getMatchId(), matchResultMsgDto.getWinningTeamId());
        log.info("updateUserScoreByMatch: {}", userEmails);
        // update the user score
        userScoreJpaRepo.updateUserScoreByEmails(userEmails, 1);
    }
}
