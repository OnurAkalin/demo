package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "models",
        indexes = {
                @Index(name = "idx_model_status", columnList = "status"),
                @Index(name = "idx_model_id_status", columnList = "id, status"),
                @Index(name = "idx_model_brand_id", columnList = "brand_id")
        }
)
public class Model extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "brand_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_model_brand")
    )
    private Brand brand;
}
