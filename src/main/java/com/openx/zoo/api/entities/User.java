package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String username;
    private String password;
    private String phone;
    private String address;

    @Column(name = "document_number")
    private String documentNumber;
}
