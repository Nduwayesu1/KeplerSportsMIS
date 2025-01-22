package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.model.RankingVolleyball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingVolleyballRepository extends JpaRepository<RankingVolleyball,Long> {
}
