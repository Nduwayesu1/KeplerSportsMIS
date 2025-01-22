package com.example.KeplerSportsMIS.controler;
import com.example.KeplerSportsMIS.model.Match;
import com.example.KeplerSportsMIS.service.MatchService;
import com.example.KeplerSportsMIS.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MatchController {

    @Autowired
    private final MatchService matchService;
    @Autowired
    private TeamService teamService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/createMatch")
    public String getMatchForm(Model model) {
        model.addAttribute("match", new Match());
        model.addAttribute("teams", teamService.findAllTeams()); // Replace with your method to fetch all teams
        return "createMatch";
    }

    // Handle form submission for creating a match
    @PostMapping("/createMatch")
    public String saveMatch(@ModelAttribute("match") Match match, Model model) {
        try {
            // Save the match using the service layer
            Match savedMatch = matchService.createMatch(match);

            // Add success message
            model.addAttribute("successMessage", "Match saved successfully with ID: " + savedMatch.getId());
        } catch (IllegalArgumentException e) {
            // Handle any validation issues
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle unexpected errors
            model.addAttribute("errorMessage", "Unexpected error occurred: " + e.getMessage());
        }

        return "createMatch"; // Reload the same form
    }
}
