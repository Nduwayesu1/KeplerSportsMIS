package com.example.KeplerSportsMIS.service;

import com.example.KeplerSportsMIS.model.Team;

import java.util.List;

public interface TeamService {

    Team registerTeam(Team team);

    List<Team> findAllTeams();

    Team findTeamById(Long id);


}
