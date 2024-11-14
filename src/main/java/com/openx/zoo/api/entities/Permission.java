package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private long id;

    private String name;

    @Column(name = "module_name")
    private String moduleName;
}
