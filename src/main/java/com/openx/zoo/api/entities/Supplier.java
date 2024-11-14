package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private long id;

    private String name;
    private String mail;
    private String phone;
    private String address;
}
