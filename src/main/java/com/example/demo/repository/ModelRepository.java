package com.example.demo.repository;

import com.example.demo.constant.Status;
import com.example.demo.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByIdAndStatus(Long id, Status status);

    List<Model> findAllByStatus(Status status);

    List<Model> findAllByBrandIdAndStatus(Long brandId, Status status);

    Page<Model> findAllByStatus(Status status, Pageable pageable);
}
