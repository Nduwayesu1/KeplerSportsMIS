package com.example.KeplerSportsMIS.service;
import com.example.KeplerSportsMIS.Enum.Points;
import com.example.KeplerSportsMIS.Repository.MatchRepository;
import com.example.KeplerSportsMIS.Repository.ScoreRepository;
import com.example.KeplerSportsMIS.model.Match;
import com.example.KeplerSportsMIS.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<Match> getAvailableMatches() {
        // Fetch all available matches
        return matchRepository.findAll();
    }

    public Score saveScore(Score score) {
        // Fetch the match from the database using the match associated with the score
        Match match = matchRepository.findById(score.getMatch().getId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        // Fetch the scores for both teams in the match
        Score team1ScoreRecord = scoreRepository.findByTeam(match.getTeam1());
        Score team2ScoreRecord = scoreRepository.findByTeam(match.getTeam2());

        // Validate the input scores (non-negative)
        if (score.getScore() < 0 || score.getScore() < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }

        // Assign the score values from the input score to the respective teams
        if (match.getTeam1().equals(score.getTeam())) {
            team1ScoreRecord.setScore(score.getScore());
        } else if (match.getTeam2().equals(score.getTeam())) {
            team2ScoreRecord.setScore(score.getScore());
        }

        // Calculate points based on scores and assign to the respective teams
        Points team1Points = calculatePoints(team1ScoreRecord, team2ScoreRecord);
        Points team2Points = calculatePoints(team2ScoreRecord, team1ScoreRecord);

        // Assign calculated points to both score records
        team1ScoreRecord.setPoints(team1Points);
        team2ScoreRecord.setPoints(team2Points);

        // Save the updated scores for both teams
        scoreRepository.save(team1ScoreRecord);
        scoreRepository.save(team2ScoreRecord);

        // Save the score and return it
        score.setPoints(team1Points); // Ensure points are set before saving
        score.setPoints(team2Points);
        return scoreRepository.save(score);
    }

    // Method to calculate points based on score comparison
    private Points calculatePoints(Score teamScoreRecord, Score opponentScoreRecord) {
        if (teamScoreRecord.getScore() > opponentScoreRecord.getScore()) {
            return Points.THREE_POINTS;
        } else if (teamScoreRecord.getScore() < opponentScoreRecord.getScore()) {
            return Points.NO_POINTS;
        } else {
            return Points.ONE_POINT;
        }
    }



    // Optional method to get a match by its ID
    public Optional<Match> getMatchById(Long matchId) {
        return matchRepository.findById(matchId);
    }
}
