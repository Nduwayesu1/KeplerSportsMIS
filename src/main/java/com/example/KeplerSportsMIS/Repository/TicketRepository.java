package com.example.KeplerSportsMIS.Repository;

import com.example.KeplerSportsMIS.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
