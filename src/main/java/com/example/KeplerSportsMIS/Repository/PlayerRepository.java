package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.model.Customer;
import com.example.KeplerSportsMIS.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    boolean existsByName(String name);
}
