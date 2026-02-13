package com.example.demo.repository;

import com.example.demo.constant.Status;
import com.example.demo.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByIdAndStatus(Long id, Status status);

    List<Brand> findAllByStatus(Status status);

    @Modifying
    @Query("UPDATE Model m SET m.status = Status.DELETED WHERE m.brand.id = :brandId")
    void softDeleteModelsByBrandId(Long brandId);
}
