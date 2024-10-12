package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "zone_employees")
public class ZoneEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zone_employee")
    private long id;

    @OneToOne
    @JoinColumn(name = "id_zone")
    private Zone zone;

    @OneToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @Column(name = "assignment_date")
    private String assignmentDate;
}
