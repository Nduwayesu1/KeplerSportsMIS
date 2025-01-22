package com.example.KeplerSportsMIS.service;

import com.example.KeplerSportsMIS.Repository.PlayerRepository;
import com.example.KeplerSportsMIS.Repository.TeamRepository;
import com.example.KeplerSportsMIS.model.Player;
import com.example.KeplerSportsMIS.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImplementation implements PlayerService {

    @Autowired
    private PlayerRepository thePlayerRepository;

    @Autowired
    private TeamRepository theTeamRepo;

    @Override
    public Player registerPlayer(Player thePlayer) {
        // Check if the player already exists
        if (thePlayerRepository.existsByName(thePlayer.getName())) {
            throw new IllegalArgumentException("Player with name '" + thePlayer.getName() + "' already exists.");
        }

        // Retrieve the team details from the player
        Team team = thePlayer.getTeam();
        if (team == null || team.getDivision() == null || team.getGender() == null || team.getTeamType() == null) {
            throw new IllegalArgumentException("Division, Gender, and Team Type are required to register a player.");
        }

        // Retrieve the team entity from the database using Division, Gender, and TeamType
        Team existingTeam = theTeamRepo.findByDivisionAndGenderAndTeamType(team.getDivision(), team.getGender(), team.getTeamType());
        if (existingTeam == null) {
            throw new IllegalArgumentException("No team found with the provided Division, Gender, and Team Type.");
        }

        // Set the existing team to the player and save the player
        thePlayer.setTeam(existingTeam);
        return thePlayerRepository.save(thePlayer);
    }

    @Override
    public List<Player> findAllPlayers() {
        return thePlayerRepository.findAll();
    }

    @Override
    public Player findById(Long id) {
        Optional<Player> player = thePlayerRepository.findById(id);

        if (player.isPresent()) {
            return player.get();  // Return the found player
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + id);  // Throw an exception if not found
        }
    }
}
