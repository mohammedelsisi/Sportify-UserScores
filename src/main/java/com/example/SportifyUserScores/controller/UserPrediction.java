package com.example.SportifyUserScores.controller;

import com.example.SportifyUserScores.model.dto.MatchSelectionDto;
import com.example.SportifyUserScores.model.orm.MatchSelection;
import com.example.SportifyUserScores.service.MatchesPredictionService;
import com.example.SportifyUserScores.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/predictions")
@CrossOrigin
public class UserPrediction {


    final MatchesPredictionService matchesPredictionService;
    @Autowired
    private UserScoreService userScoreService;
    public UserPrediction(MatchesPredictionService matchesPredictionService) {
        this.matchesPredictionService = matchesPredictionService;
    }


    @PostMapping
    public ResponseEntity<Object> saveSelection(@RequestBody MatchSelectionDto matchSelection, HttpServletRequest request) {
        long id = matchesPredictionService.saveUserSelection(matchSelection);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("location", request.getRequestURL() + "/" + id);
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @PostMapping("/check")
    public ResponseEntity<Object> checkSelected(@RequestBody MatchSelectionDto matchSelection) {
        var matchSelectionCheck = matchesPredictionService.getIfPresent(matchSelection);
        if(matchSelectionCheck!=null)
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(matchSelectionCheck);
        else return ResponseEntity.status(HttpStatus.CONTINUE).body(new MatchSelectionDto());
    }

    @GetMapping("/{id}")
    public MatchSelectionDto getSelection(@PathVariable long id) {
        return matchesPredictionService.getSelection(id);

    }

    @GetMapping
    public ResponseEntity<List<MatchSelection>> getAllSelection() {
        List<MatchSelection> all = matchesPredictionService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/score")
    public Integer getUserScore (String userEmail){
        return  userScoreService.getUserScore(userEmail);
    }
}
