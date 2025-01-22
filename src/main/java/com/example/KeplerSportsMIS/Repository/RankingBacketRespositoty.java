package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.model.RankingVolleyball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingBacketRespositoty extends JpaRepository<RankingVolleyball,Long> {
}
