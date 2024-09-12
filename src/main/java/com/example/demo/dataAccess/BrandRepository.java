package com.example.demo.dataAccess;

import com.example.demo.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	@Modifying
	@Query(value = "update models set arsiv_tarihi = NOW() + INTERVAL '120 MONTH' where brand_id = :id", nativeQuery = true)
	void updateRelatedTables(int id);
}
