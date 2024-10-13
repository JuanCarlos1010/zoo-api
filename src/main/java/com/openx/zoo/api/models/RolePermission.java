package com.openx.zoo.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role_permissions")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
