package com.example.admin.repository;

import com.example.admin.model.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
