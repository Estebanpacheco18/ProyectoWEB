package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.admin.model.Estudiante;
import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Métodos personalizados
    Estudiante findByNombre(String nombre);

    // Filtrar por el atributo seccion como String
    List<Estudiante> findBySeccion(String seccion);
}
