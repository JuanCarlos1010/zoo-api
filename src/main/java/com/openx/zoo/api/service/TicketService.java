package com.openx.zoo.api.service;

import com.openx.zoo.api.entity.Ticket;
import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.repository.TicketRepository;
import com.openx.zoo.api.utils.UniqueCodeGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAllTickets() {
        try {
            return ticketRepository.findAllByDeletedAtIsNull();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public Ticket findTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket no encontrado con el id: " + id));
    }

    @Transactional
    public Ticket createTicket(Ticket ticket) {
        if (ticket.getPrice() < 0.0d) {
            throw new BadRequestException("El campo 'precio' es obligatorio.");
        }

        if (ticket.getVisitorName() == null) {
            throw new BadRequestException("El campo 'Nombre del visitante' es obligatorio.");
        }
        ticket.setCode(UniqueCodeGenerator.generate());
        ticket.setVisitDate(LocalDateTime.now());
        ticket.setCreatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket updateTicket(Ticket updateTicket) {
        if (updateTicket.getPrice() < 0.0d) {
            throw new BadRequestException("El campo 'precio' es obligatorio.");
        }

        if (updateTicket.getVisitorName() == null) {
            throw new BadRequestException("El campo 'Nombre del visitante' es obligatorio.");
        }
        return ticketRepository.findById(updateTicket.getId())
                .map(ticket -> {
                    ticket.setUser(updateTicket.getUser());
                    ticket.setType(updateTicket.getType());
                    ticket.setVisitDate(LocalDateTime.now());
                    ticket.setUpdatedAt(LocalDateTime.now());
                    ticket.setPrice(updateTicket.getPrice());
                    ticket.setStatus(updateTicket.isStatus());
                    ticket.setVisitorName(updateTicket.getVisitorName());
                    ticket.setPaymentMethod(updateTicket.getPaymentMethod());
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(() -> new NotFoundException("Ticket no econtrado con id: " + updateTicket.getId()));
    }

    @Transactional
    public boolean deleteAnimal(Long id) {
        try {
            Ticket ticket = findTicketById(id);
            ticket.setDeletedAt(LocalDateTime.now());
            ticketRepository.save(ticket);
            return true;
        } catch (NotFoundException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }
}
