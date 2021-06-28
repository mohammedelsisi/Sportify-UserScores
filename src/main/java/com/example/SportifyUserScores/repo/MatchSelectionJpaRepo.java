package com.example.SportifyUserScores.repo;

import com.example.SportifyUserScores.model.dto.MatchResultHistoryDto;
import com.example.SportifyUserScores.model.orm.MatchSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchSelectionJpaRepo extends JpaRepository<MatchSelection,Long> {

    @Query("select new com.example.SportifyUserScores.model.dto.MatchResultHistoryDto(ms.userEmail,ms.matchId,ms.selectedTeamId,mr.winningTeamId) from MatchSelection ms , MatchResult mr where ms.matchId=mr.matchId and ms.userEmail=?1")
    List<MatchResultHistoryDto> getFinishedMatches(String userEmail);
    @Query("from MatchSelection ms where ms.matchId not in (select matchId from MatchResult) and ms.userEmail=?1")
    List<MatchSelection> getNotFinishedMatches(String userEmail);
    MatchSelection queryByMatchIdAndUserEmail(Long matchId,String userEmail);
    @Query("select m.userEmail from MatchSelection m where m.matchId = ?1 and m.selectedTeamId = ?2")
    List<String> queryUserEmailByMatchIdAndSelectedTeamId(long matchId, long selectedTeamId);
}
