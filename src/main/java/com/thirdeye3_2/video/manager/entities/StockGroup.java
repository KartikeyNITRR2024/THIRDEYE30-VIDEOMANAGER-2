package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "stock_group",
    indexes = {
        @Index(name = "idx_stock_group_active", columnList = "active")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(
        mappedBy = "group",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<Stock> listOfStock;
}
