package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.model.Score;
import com.example.KeplerSportsMIS.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long> {
    Score findByTeam(Team team1);
}
