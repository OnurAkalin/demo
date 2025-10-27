package com.example.demo.repositories;

import com.example.demo.constants.Status;
import com.example.demo.entities.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByIdAndStatus(Long id, Status status);

    List<Model> findAllByStatus(Status status);

    Page<Model> findAllByStatus(Status status, Pageable pageable);
}
