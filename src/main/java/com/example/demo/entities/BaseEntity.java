package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "yaratilma_zamani")
	private Date yaratilmaZamani;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "guncelleme_zamani")
	private Date guncellemeZamani;

	@PrePersist
	protected void onCreate() {
		yaratilmaZamani = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		guncellemeZamani = new Date();
	}
}
