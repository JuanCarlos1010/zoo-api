package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private long id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String type;

    @Column(name = "visite_date")
    private LocalDateTime visiteDate;

    private Double price;

    @Column(name = "visitor_name")
    private String visitorName;

    @Column(name = "payment_method")
    private String paymentMethod;

    private boolean status;
}
