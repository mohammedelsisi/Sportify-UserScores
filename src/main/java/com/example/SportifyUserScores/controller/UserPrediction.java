package com.example.SportifyUserScores.controller;

import com.example.SportifyUserScores.model.dto.MatchSelectionDto;
import com.example.SportifyUserScores.model.orm.MatchSelection;
import com.example.SportifyUserScores.service.MatchesPredictionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/predictions")
public class UserPrediction {


    final MatchesPredictionService matchesPredictionService;

    public UserPrediction(MatchesPredictionService matchesPredictionService) {
        this.matchesPredictionService = matchesPredictionService;
    }


    @PostMapping
    public ResponseEntity<Object> saveSelected(@RequestBody MatchSelectionDto matchSelection, HttpServletRequest request) {
        long id = matchesPredictionService.saveUserSelection(matchSelection);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("location", request.getRequestURL() + "/" + id);
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @GetMapping("/{id}")
    public MatchSelection getSelection(@PathVariable long id) {
        return matchesPredictionService.getSelection(id);

    }

    @GetMapping
    public ResponseEntity<List<MatchSelection>> getAllSelection() {
        List<MatchSelection> all = matchesPredictionService.getAll();
        return ResponseEntity.ok(all);
    }
}
