package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.model.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping,Long> {
}
