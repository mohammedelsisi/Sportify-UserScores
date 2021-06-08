package com.example.SportifyUserScores.repo;

import com.example.SportifyUserScores.model.orm.MatchSelection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchSelectionDao extends JpaRepository<MatchSelection,Long> {
}
