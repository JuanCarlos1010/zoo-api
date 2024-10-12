package com.openx.zoo.apizoogestion.repositories;

import com.openx.zoo.apizoogestion.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
