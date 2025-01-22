package com.example.KeplerSportsMIS.service;

import com.example.KeplerSportsMIS.model.Player;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface PlayerService {
    Player  registerPlayer(Player  thePlayer);

    List<Player> findAllPlayers();

    Player findById(Long id);
}
