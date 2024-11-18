package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.admin.model.Actividad;
import java.util.List;


@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    // Métodos personalizados si se necesitan
    List<Actividad> findByNombreContaining(String nombre);
}
