package com.example.SportifyUserScores.repo;

import com.example.SportifyUserScores.model.orm.MatchSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchSelectionJpaRepo extends JpaRepository<MatchSelection,Long> {

    boolean existsByMatchIdAndAndUserEmail(Long matchId,String userEmail);
    MatchSelection queryByMatchIdAndUserEmail(Long matchId,String userEmail);
    @Query("select m.userEmail from MatchSelection m where m.matchId = ?1 and m.selectedTeamId = ?2")
    List<String> queryUserEmailByMatchIdAndSelectedTeamId(long matchId, long selectedTeamId);
}
