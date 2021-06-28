package com.example.SportifyUserScores.repo;

import com.example.SportifyUserScores.model.orm.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserScoreJpaRepo extends JpaRepository<UserScore, String> {
    @Transactional
    @Modifying
    @Query("update UserScore u set u.score = u.score + ?2 where u.userEmail in ?1")
    void updateUserScoreByEmails(List<String> emails, int score);

    UserScore queryByUserEmail(String userEmail);
}
