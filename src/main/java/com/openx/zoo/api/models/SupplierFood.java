package com.openx.zoo.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "supplier_foods")
public class SupplierFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int amount;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "supply_date")
    private String supplyDate;

    @Column(name = "expiration_date")
    private String expirationDate;
}
