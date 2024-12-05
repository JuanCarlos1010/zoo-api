package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.TicketDTO;
import com.openx.zoo.api.entity.Ticket;
import com.openx.zoo.api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper extends AbstractMapper<Ticket, TicketDTO> {

    @Override
    public TicketDTO toDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .code(ticket.getCode())
                .type(ticket.getType())
                .price(ticket.getPrice())
                .status(ticket.isStatus())
                .visitDate(ticket.getVisitDate())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .userId(ticket.getUser().getId())
                .visitorName(ticket.getVisitorName())
                .paymentMethod(ticket.getPaymentMethod())
                .userName(ticket.getUser().getFullName())
                .build();
    }

    @Override
    public Ticket toEntity(TicketDTO ticket) {
        return Ticket.builder()
                .id(ticket.getId())
                .type(ticket.getType())
                .code(ticket.getCode())
                .price(ticket.getPrice())
                .status(ticket.isStatus())
                .visitDate(ticket.getVisitDate())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .user(new User(ticket.getUserId()))
                .visitorName(ticket.getVisitorName())
                .paymentMethod(ticket.getPaymentMethod())
                .build();
    }
}
