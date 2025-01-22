package com.example.KeplerSportsMIS.model;

import jakarta.persistence.*;
import com.example.KeplerSportsMIS.Enum.Gender;
import com.example.KeplerSportsMIS.Enum.Division;
import com.example.KeplerSportsMIS.Enum.Team_Type;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Division division;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Team_Type teamType;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RankingBasket> basketballRankings;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RankingVolleyball> volleyballRankings;

    // Add relationships for matches
    @OneToMany(mappedBy = "team1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matchesAsTeam1;

    @OneToMany(mappedBy = "team2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matchesAsTeam2;

    public Team() {}

    public Team(String name, Division division, Gender gender, Team_Type teamType) {
        this.name = name;
        this.division = division;
        this.gender = gender;
        this.teamType = teamType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Team_Type getTeamType() {
        return teamType;
    }

    public void setTeamType(Team_Type teamType) {
        this.teamType = teamType;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<RankingBasket> getBasketballRankings() {
        return basketballRankings;
    }

    public void setBasketballRankings(List<RankingBasket> basketballRankings) {
        this.basketballRankings = basketballRankings;
    }

    public List<RankingVolleyball> getVolleyballRankings() {
        return volleyballRankings;
    }

    public void setVolleyballRankings(List<RankingVolleyball> volleyballRankings) {
        this.volleyballRankings = volleyballRankings;
    }

    public List<Match> getMatchesAsTeam1() {
        return matchesAsTeam1;
    }

    public void setMatchesAsTeam1(List<Match> matchesAsTeam1) {
        this.matchesAsTeam1 = matchesAsTeam1;
    }

    public List<Match> getMatchesAsTeam2() {
        return matchesAsTeam2;
    }

    public void setMatchesAsTeam2(List<Match> matchesAsTeam2) {
        this.matchesAsTeam2 = matchesAsTeam2;
    }
}
