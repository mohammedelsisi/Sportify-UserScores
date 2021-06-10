package com.example.SportifyUserScores.service;

import com.example.SportifyUserScores.model.dto.MatchSelectionDto;
import com.example.SportifyUserScores.model.orm.MatchSelection;
import com.example.SportifyUserScores.repo.MatchSelectionJpaRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MatchesPredictionService {

    final MatchSelectionJpaRepo matchSelectionJpaRepo;
    final ModelMapper modelMapper;

    public MatchesPredictionService(MatchSelectionJpaRepo matchSelectionJpaRepo, ModelMapper modelMapper) {
        this.matchSelectionJpaRepo = matchSelectionJpaRepo;
        this.modelMapper = modelMapper;
    }

    public long saveUserSelection(MatchSelectionDto matchSelectionDto){
        MatchSelection map = modelMapper.map(matchSelectionDto, MatchSelection.class);
        MatchSelection save = matchSelectionJpaRepo.save(map);
        return save.getId();
    }

    public MatchSelection getSelection(long matchSelection) {
        Optional<MatchSelection> byId = matchSelectionJpaRepo.findById(matchSelection);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new EntityNotFoundException();
    }

    public List<MatchSelection> getAll() {
        return matchSelectionJpaRepo.findAll();
    }
}
