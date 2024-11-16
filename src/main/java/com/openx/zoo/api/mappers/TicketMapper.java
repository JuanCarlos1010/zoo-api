package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.TicketDTO;
import com.openx.zoo.api.entities.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper extends AbstractMapper<Ticket, TicketDTO> {
    private final UserMapper userMapper;

    public TicketMapper(final UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public TicketDTO toDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .type(ticket.getType())
                .price(ticket.getPrice())
                .status(ticket.isStatus())
                .visitDate(ticket.getVisitDate())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .visitorName(ticket.getVisitorName())
                .paymentMethod(ticket.getPaymentMethod())
                .user(userMapper.toDTO(ticket.getUser()))
                .build();
    }

    @Override
    public Ticket toEntity(TicketDTO ticket) {
        return Ticket.builder()
                .id(ticket.getId())
                .type(ticket.getType())
                .price(ticket.getPrice())
                .status(ticket.isStatus())
                .visitDate(ticket.getVisitDate())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .visitorName(ticket.getVisitorName())
                .paymentMethod(ticket.getPaymentMethod())
                .user(userMapper.toEntity(ticket.getUser()))
                .build();
    }
}
