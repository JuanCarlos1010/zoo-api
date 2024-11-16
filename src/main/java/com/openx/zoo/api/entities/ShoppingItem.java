package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_items")
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double quantity;
    private double price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @OneToOne
    @JoinColumn(name = "shopping_id")
    private Shopping shopping;
}
