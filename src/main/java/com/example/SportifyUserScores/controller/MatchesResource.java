package com.example.SportifyUserScores.controller;

import com.example.SportifyUserScores.model.dto.MatchResultHistoryDto;
import com.example.SportifyUserScores.model.dto.MatchSelectionDto;
import com.example.SportifyUserScores.service.MatchesPredictionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/matches")
public class MatchesResource {

    final
    MatchesPredictionService matchesPredictionService;

    public MatchesResource(MatchesPredictionService matchesPredictionService) {
        this.matchesPredictionService = matchesPredictionService;
    }
    @GetMapping
    public List<MatchResultHistoryDto> getFinishedMatches(String userEmail){
       return matchesPredictionService.getFinishedMatches(userEmail);
    }
    @GetMapping("/notfinished")
    public List<MatchSelectionDto> getNotFinishedMatches(String userEmail){
       return matchesPredictionService.getNotFinishedMatches(userEmail);
    }
}
