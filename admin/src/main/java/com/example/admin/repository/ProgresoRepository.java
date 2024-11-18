package com.example.admin.repository;

import com.example.admin.model.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgresoRepository extends JpaRepository<Progreso, Long> {
    List<Progreso> findByTrimestre(String trimestre); // Filtra por trimestre
}