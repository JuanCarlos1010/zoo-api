package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private long id;

    private String name;
    private String position;
    private String phone;
    private String address;
    private String description;
}
