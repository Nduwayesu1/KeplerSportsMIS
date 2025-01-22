package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.Enum.Division;
import com.example.KeplerSportsMIS.Enum.Gender;
import com.example.KeplerSportsMIS.Enum.Team_Type;
import com.example.KeplerSportsMIS.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    boolean existsByName(String name);


    Team findByDivisionAndGenderAndTeamType(Division division, Gender gender, Team_Type teamType);

    Optional<Object> findByName(String name);
}
