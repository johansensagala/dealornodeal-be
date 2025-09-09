package com.game.dealornodeal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "verse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Verse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String reference;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
