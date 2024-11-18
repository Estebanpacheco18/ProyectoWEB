package com.example.admin.service;

import com.example.admin.model.Nota;
import com.example.admin.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota saveNota(Nota nota) {
        return notaRepository.save(nota); // Guarda una nueva nota si no existe
    }

    // Obtener todas las notas
    public List<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    // Obtener una nota por ID
    public Optional<Nota> getNotaById(Long id) {
        return notaRepository.findById(id);
    }

    // Eliminar una nota
    public void deleteNota(Long id) {
        notaRepository.deleteById(id);
    }
}