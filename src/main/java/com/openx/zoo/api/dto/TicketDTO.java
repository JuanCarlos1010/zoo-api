package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketDTO {
    private long id;
    private boolean status;
    private double price;
    private String type;
    private LocalDateTime visitDate;
    private String visitorName;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
