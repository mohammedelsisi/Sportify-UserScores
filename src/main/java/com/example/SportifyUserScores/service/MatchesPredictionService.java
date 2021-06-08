package com.example.SportifyUserScores.service;

import com.example.SportifyUserScores.model.dto.MatchSelectionDto;
import com.example.SportifyUserScores.model.orm.MatchSelection;
import com.example.SportifyUserScores.repo.MatchSelectionDao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MatchesPredictionService {

    final  MatchSelectionDao matchSelectionDao;
    final ModelMapper modelMapper;

    public MatchesPredictionService(MatchSelectionDao matchSelectionDao, ModelMapper modelMapper) {
        this.matchSelectionDao = matchSelectionDao;
        this.modelMapper = modelMapper;
    }

    public long saveUserSelection(MatchSelectionDto matchSelectionDto){
        MatchSelection map = modelMapper.map(matchSelectionDto, MatchSelection.class);
        MatchSelection save = matchSelectionDao.save(map);
        return save.getId();
    }

    public MatchSelection getSelection(long matchSelection) {
        Optional<MatchSelection> byId = matchSelectionDao.findById(matchSelection);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new EntityNotFoundException();
    }

    public List<MatchSelection> getAll() {
        return matchSelectionDao.findAll();
    }
}
