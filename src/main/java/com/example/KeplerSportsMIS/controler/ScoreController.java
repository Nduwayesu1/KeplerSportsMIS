package com.example.KeplerSportsMIS.controler;
import com.example.KeplerSportsMIS.Repository.MatchRepository;
import com.example.KeplerSportsMIS.Repository.ScoreRepository;
import com.example.KeplerSportsMIS.model.Match;
import com.example.KeplerSportsMIS.model.Score;
import com.example.KeplerSportsMIS.Enum.Points; // Make sure to import Points enum
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ScoreController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @PostMapping("/createScore")
    public String addScore(
            @RequestParam Long matchId,
            @RequestParam int team1Score,
            @RequestParam int team2Score,
            Model model) {
        try {
            // Validate that scores are non-negative
            if (team1Score < 0 || team2Score < 0) {
                throw new IllegalArgumentException("Scores cannot be negative.");
            }

            // Retrieve the match using matchId
            Match match = matchRepository.findById(matchId)
                    .orElseThrow(() -> new RuntimeException("Match not found"));

            // Create Score objects for each team
            Score score1 = new Score();
            score1.setTeam(match.getTeam1());
            score1.setMatch(match);
            score1.setScore(team1Score);
            score1.setPoints(calculatePoints(team1Score, team2Score)); // Set points based on score comparison

            Score score2 = new Score();
            score2.setTeam(match.getTeam2());
            score2.setMatch(match);
            score2.setScore(team2Score);
            score2.setPoints(calculatePoints(team2Score, team1Score)); // Set points based on score comparison

            // Save scores
            scoreRepository.save(score1);
            scoreRepository.save(score2);

            // Add success message
            model.addAttribute("message", "Scores added successfully for match: "
                    + match.getTeam1().getName() + " vs " + match.getTeam2().getName());
            model.addAttribute("success", true);

        } catch (Exception e) {
            // Add failure message in case of any error
            model.addAttribute("message", "Error occurred while adding scores: " + e.getMessage());
            model.addAttribute("success", false);
        }

        // Redirect or return the same page
        List<Match> matches = matchRepository.findAll(); // Refresh the match list
        model.addAttribute("matches", matches);
        return "scoreForm"; // Return to the score form page
    }

    @GetMapping("/createScore")
    public String showScoreForm(Model model) {
        List<Match> matches = matchRepository.findAll(); // Retrieve the list of matches
        model.addAttribute("matches", matches);
        return "scoreForm"; // Thymeleaf template name
    }

    // Method to calculate points based on the scores
    private Points calculatePoints(int teamScore, int opponentScore) {
        if (teamScore > opponentScore) {
            return Points.THREE_POINTS;
        } else if (teamScore < opponentScore) {
            return Points.NO_POINTS;
        } else {
            return Points.ONE_POINT;
        }
    }
}
