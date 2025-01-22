package com.example.KeplerSportsMIS.service;

import com.example.KeplerSportsMIS.model.Match;
import com.example.KeplerSportsMIS.model.Score;

import java.util.List;

public interface ScoreService {
    List<Match> getAvailableMatches();
    Score saveScore(Score score);
}
