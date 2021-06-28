package com.example.SportifyUserScores.repo;

import com.example.SportifyUserScores.model.orm.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchResultRepo extends JpaRepository<MatchResult,Long> {
}
