package com.example.SportifyUserScores.broker;

import com.example.SportifyUserScores.model.dto.broker.MatchResultMsgDto;
import com.example.SportifyUserScores.service.UserScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HandleMatchResultMsgs {

    private final UserScoreService userScoreService;

    public HandleMatchResultMsgs(UserScoreService userScoreService) {
        this.userScoreService = userScoreService;
    }

    @ServiceActivator(inputChannel = "finishedMatchesChannel")
    public void handleMatchResult(@Payload MatchResultMsgDto matchResultMsgDto) {
        log.info("HandleMatchResultMsgs.handleMatchResult: {}", matchResultMsgDto);

        userScoreService.updateUserScoreByMatch(matchResultMsgDto);
    }

}
