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
@Table(name = "models")
public class Model extends BaseEntity {
	@Column(name = "name")
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "brand_id")
	private Brand brand;
}
