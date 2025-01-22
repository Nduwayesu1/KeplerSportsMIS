package com.example.KeplerSportsMIS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "game_match") // Avoid reserved keywords
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    @Column(nullable = true)
    private String location;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores;

    public Match() {}

    public Match(Team team1, Team team2, LocalDateTime matchDate, String location) {
        this.team1 = team1;
        this.team2 = team2;
        this.matchDate = matchDate;
        this.location = location;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
