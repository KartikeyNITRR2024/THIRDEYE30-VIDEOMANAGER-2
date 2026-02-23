package com.thirdeye3_2.video.manager.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "stock",
    indexes = {
        @Index(name = "idx_stock_market_code", columnList = "market_code"),
        @Index(name = "idx_stock_active", columnList = "active"),
        @Index(name = "idx_stock_group_id", columnList = "group_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "market_code", nullable = false)
    private String marketCode;

    @Column(name = "stock_name", nullable = false)
    private String stockName;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private StockGroup group;
}
