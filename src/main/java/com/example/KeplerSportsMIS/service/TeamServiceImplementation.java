package com.example.KeplerSportsMIS.service;

import com.example.KeplerSportsMIS.Repository.TeamRepository;
import com.example.KeplerSportsMIS.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImplementation implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team registerTeam(Team team) {
        // Check if a team already exists with the same Division, Gender, and Team_Type
        Team existingTeam = teamRepository.findByDivisionAndGenderAndTeamType(
                team.getDivision(), team.getGender(), team.getTeamType());

        // If an existing team with the same Division, Gender, and Team_Type is found, return null or handle it
        if (existingTeam != null) {
            return null; // or throw new IllegalArgumentException("A team with the same Division, Gender, and Team Type already exists.");
        }

        // Save and return the new team if it doesn't exist with the same Division, Gender, and Team_Type
        return teamRepository.save(team);
    }


    @Override
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team findTeamById(Long id) {
        // Check if the team exists, or throw an exception if not found
        return teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team with ID " + id + " not found"));
    }
}
