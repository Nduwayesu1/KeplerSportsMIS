package com.example.KeplerSportsMIS.service;

import com.example.KeplerSportsMIS.Repository.MatchRepository;
import com.example.KeplerSportsMIS.Repository.TeamRepository;
import com.example.KeplerSportsMIS.model.Match;
import com.example.KeplerSportsMIS.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Match createMatch(Match theMatch) {
        // Validate teams
        Optional<Team> team1 = teamRepository.findById(theMatch.getTeam1().getId());
        Optional<Team> team2 = teamRepository.findById(theMatch.getTeam2().getId());

        if (team1.isEmpty() || team2.isEmpty()) {
            throw new IllegalArgumentException("One or both teams do not exist");
        }

        // Validate that no team has multiple matches on the same day
        LocalDateTime matchDate = theMatch.getMatchDate();
        LocalDateTime startOfDay = matchDate.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        boolean team1HasMatch = matchRepository.existsByTeam1OrTeam2AndMatchDateBetween(
                team1.get(), team1.get(), startOfDay, endOfDay);
        boolean team2HasMatch = matchRepository.existsByTeam1OrTeam2AndMatchDateBetween(
                team2.get(), team2.get(), startOfDay, endOfDay);

        if (team1HasMatch || team2HasMatch) {
            throw new IllegalArgumentException("One or both teams already have a match scheduled on this day");
        }

        // Save the match
        theMatch.setTeam1(team1.get());
        theMatch.setTeam2(team2.get());
        return matchRepository.save(theMatch);
    }
}
