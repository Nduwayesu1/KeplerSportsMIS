package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.model.Match;
import com.example.KeplerSportsMIS.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    boolean existsByTeam1OrTeam2AndMatchDateBetween(Team team1, Team team2, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
