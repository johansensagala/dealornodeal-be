package com.game.dealornodeal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "copper")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Copper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String type; // "reward" / "punishment"

    @Column(columnDefinition = "TEXT")
    private String description;
}
