package com.example.SportifyUserScores.service;

import com.example.SportifyUserScores.model.dto.broker.MatchResultMsgDto;
import com.example.SportifyUserScores.model.dto.broker.NewUserMessage;
import com.example.SportifyUserScores.model.orm.*;
import com.example.SportifyUserScores.repo.MatchResultRepo;
import com.example.SportifyUserScores.repo.MatchSelectionJpaRepo;
import com.example.SportifyUserScores.repo.UserScoreJpaRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserScoreService {
    private final UserScoreJpaRepo userScoreJpaRepo;
    private final MatchSelectionJpaRepo matchSelectionJpaRepo;
    private final MatchResultRepo matchResultRepo;
    private final ModelMapper modelMapper;


    public UserScoreService(UserScoreJpaRepo userScoreJpaRepo, MatchSelectionJpaRepo matchSelectionJpaRepo, MatchResultRepo matchResultRepo, ModelMapper modelMapper) {
        this.userScoreJpaRepo = userScoreJpaRepo;
        this.matchSelectionJpaRepo = matchSelectionJpaRepo;
        this.matchResultRepo = matchResultRepo;
        this.modelMapper = modelMapper;
    }

    // todo users must already exist in the db
    public void updateUserScoreByMatch(MatchResultMsgDto matchResultMsgDto) {
        // find winning user emails using match id
        List<String> userEmails = matchSelectionJpaRepo.queryUserEmailByMatchIdAndSelectedTeamId(matchResultMsgDto.getMatchId(), matchResultMsgDto.getWinningTeamId());

        // update the user score
        matchResultRepo.save(modelMapper.map(matchResultMsgDto,MatchResult.class));
        userScoreJpaRepo.updateUserScoreByEmails(userEmails, 1);
        //TODO notify winners

    }

    public void CreateUserZeroScore(NewUserMessage newUserMessage) {
        UserScore userScore = new UserScore(newUserMessage.getEmail(), 0);
        userScoreJpaRepo.save(userScore);
    }

    public Integer getUserScore(String userEmail) {
        UserScore userScore = userScoreJpaRepo.queryByUserEmail(userEmail);
        return userScore.getScore();
    }
}
