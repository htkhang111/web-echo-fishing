package com.echommo.fishing.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_maps")
@Data
public class GameMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Tên map

    @Column(name = "key_code", unique = true, nullable = false)
    private String keyCode; // Mã định danh (VD: 'map_01')

    private int width;
    private int height;

    @Column(name = "json_path")
    private String jsonPath;

    @Column(name = "tileset_path")
    private String tilesetPath;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}