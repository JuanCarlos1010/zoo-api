package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "supplier_foods")
public class SupplierFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    @OneToOne
    @JoinColumn(name = "id_food")
    private Food food;

    @Column(name = "date_of_supply")
    private String dateOfSupply;

    private int amount;
}
