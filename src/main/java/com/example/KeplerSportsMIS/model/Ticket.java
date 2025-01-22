package com.example.KeplerSportsMIS.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate gameDate;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;

    @Column(nullable = false)
    private double price;

    public Ticket() {}

    public Ticket(LocalDate gameDate, Team team1, Team team2, double price) {
        this.gameDate = gameDate;
        this.team1 = team1;
        this.team2 = team2;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
