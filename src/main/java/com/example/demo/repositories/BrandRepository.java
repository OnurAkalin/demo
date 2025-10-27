package com.example.demo.repositories;

import com.example.demo.constants.Status;
import com.example.demo.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByIdAndStatus(Long id, Status status);

    List<Brand> findAllByStatus(Status status);
}
