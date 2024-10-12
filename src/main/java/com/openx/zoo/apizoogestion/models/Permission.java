package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission")
    private long id;

    private String name;

    @Column(name = "module_name")
    private String moduleName;
}
