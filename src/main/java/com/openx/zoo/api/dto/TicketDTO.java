package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketDTO {
    private long id;
    private long userId;
    private String type;
    private String code;
    private double price;
    private boolean status;
    private String userName;
    private String visitorName;
    private String paymentMethod;
    private LocalDateTime visitDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
