package com.example.admin.service;

import com.example.admin.model.Progreso;
import com.example.admin.repository.ProgresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgresoService {

    @Autowired
    private ProgresoRepository progresoRepository;

    public List<Progreso> getAllProgresos() {
        return progresoRepository.findAll();
    }

    public Optional<Progreso> getProgresoById(Long id) {
        return progresoRepository.findById(id);
    }

    public void saveProgreso(Progreso progreso) {
        progresoRepository.save(progreso);
    }

    public void updateProgreso(Progreso progreso) {
        progresoRepository.save(progreso); // Save the updated progress
    }

    public void deleteProgreso(Long id) {
        progresoRepository.deleteById(id);
    }

    // Método para obtener los progresos filtrados solo por trimestre
    public List<Progreso> getFilteredProgresos(String trimestre) {
        if (trimestre != null && !trimestre.isEmpty()) {
            return progresoRepository.findByTrimestre(trimestre); // Filtrar por trimestre
        } else {
            return progresoRepository.findAll(); // Si no hay filtro, devolver todos
        }
    }
}
