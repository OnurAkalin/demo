package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "brands",
        indexes = {
                @Index(name = "idx_brand_status", columnList = "status"),
                @Index(name = "idx_brand_id_status", columnList = "id, status")
        }
)
public class Brand extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Model> models = new ArrayList<>();
}
