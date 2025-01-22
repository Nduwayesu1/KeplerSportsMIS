package com.example.KeplerSportsMIS.controler;

import com.example.KeplerSportsMIS.model.Player;
import com.example.KeplerSportsMIS.model.Team;
import com.example.KeplerSportsMIS.service.PlayerService;
import com.example.KeplerSportsMIS.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService; // Service to fetch teams

    // Display the player registration page
    @GetMapping("/playerRegistration")
    public String playerPage(Model model) {
        model.addAttribute("player", new Player()); // Form binding
        List<Team> teams = teamService.findAllTeams(); // Fetch teams
        model.addAttribute("teams", teams); // Add teams to model
        return "registerPlayer"; // View name
    }

    // Handle player registration form submission
    @PostMapping("/playerRegistration")
    public String savePlayer(@ModelAttribute("player") Player player, Model model, RedirectAttributes redirectAttributes) {
        try {
            // Ensure player.team.id is set correctly based on the selected team
            Team selectedTeam = teamService.findTeamById(player.getTeam().getId());

            player.setTeam(selectedTeam);  // Set the actual team object for the player

            Player savedPlayer = playerService.registerPlayer(player); // Save player

            // Add success message and redirect on success using flash attributes
            redirectAttributes.addFlashAttribute("successMessage", "Player successfully registered!");
            return "redirect:/playerRegistration";
        } catch (IllegalArgumentException e) {
            // Handle errors such as duplicate entries or invalid input
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        // Reload team list in case of error
        List<Team> teams = teamService.findAllTeams();
        model.addAttribute("teams", teams);
        return "registerPlayer";
    }
}
