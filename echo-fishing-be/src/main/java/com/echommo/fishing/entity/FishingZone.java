package com.echommo.fishing.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fishing_zones")
@Data
public class FishingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private GameMap gameMap;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "min_level")
    private int minLevel;

    // Lưu tọa độ X, Y, W, H của vùng nước
    private int x;
    private int y;
    private int width;
    private int height;
}