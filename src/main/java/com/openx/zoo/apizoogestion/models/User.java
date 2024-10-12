package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;

    @OneToOne
    @JoinColumn(name = "id_role")
    private Role role;

    private String username;
    private String password;
    private String phone;

    @Column(name = "document_number")
    private String documentNumber;

    private String address;
}
