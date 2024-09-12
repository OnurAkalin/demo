package com.example.demo.entities;

import com.example.demo.common.utils.DateHelper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "brands")
public class Brand extends BaseEntity {
	@Column(name = "name")
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arsiv_tarihi")
	private Date arsivTarihi;

	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private List<Model> models;

	@Override
	protected void onCreate() {
		super.onCreate();
		arsivTarihi = DateHelper.addYearsToInstanceOfDate(10);
	}
}
