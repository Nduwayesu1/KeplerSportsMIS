package com.example.KeplerSportsMIS.controler;

import com.example.KeplerSportsMIS.model.Team;
import com.example.KeplerSportsMIS.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teamRegistration")
    public String registerTeamForm(Model model) {
        // Add an empty Team object to the model for binding with the form
        model.addAttribute("team", new Team());
        return "registerTeam";
    }

    @PostMapping("/teamRegistration")
    public String saveTeam(@ModelAttribute("team") Team team, Model model) {
        // Save the team, check if already exists
        Team savedTeam = teamService.registerTeam(team);

        if (savedTeam == null) {
            // Team already exists, add error message
            model.addAttribute("errorMessage", "Team with this name already exists!");
            return "registerTeam";
        }

        // Add success message
        model.addAttribute("successMessage", "Team successfully registered!");
        return "registerTeam";
    }
}
