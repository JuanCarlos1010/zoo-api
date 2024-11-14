package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String type;
    private double price;
    private boolean status;

    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    @Column(name = "visitor_name")
    private String visitorName;

    @Column(name = "payment_method")
    private String paymentMethod;
}
