package com.example.admin.controller;

import com.example.admin.model.Progreso;
import com.example.admin.model.Nota;
import com.example.admin.service.ProgresoService;
import com.example.admin.service.EstudianteService;
import com.example.admin.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/progreso")
public class ProgresoController {

    @Autowired
    private ProgresoService progresoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private NotaService notaService;

    // Método actualizado para listar progresos con filtro por trimestre
    @GetMapping
    public String listProgresos(@RequestParam(value = "trimestre", required = false) String trimestre,
                                Model model) {
        // Si se proporciona un filtro de trimestre, usarlo para obtener los progresos filtrados
        List<Progreso> progresos = progresoService.getFilteredProgresos(trimestre);

        // Agregar los progresos al modelo
        model.addAttribute("progresos", progresos);

        // Agregar los estudiantes al modelo (para el formulario)
        model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
        return "progreso/list";  // Nombre de la vista
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("progreso", new Progreso());
        model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
        return "progreso/form";
    }

    @PostMapping
    public String createProgreso(@ModelAttribute Progreso progreso) {
        if (progreso.getNota() != null && progreso.getNota().getId() == null) {
            // Si la nota es nueva (no tiene ID), guardarla primero
            Nota savedNota = notaService.saveNota(progreso.getNota());
            progreso.setNota(savedNota);  // Asignar la nota guardada al progreso
        }
        progresoService.saveProgreso(progreso); // Guardar el progreso con la nota asociada
        return "redirect:/progreso";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Progreso> progreso = progresoService.getProgresoById(id);
        if (progreso.isPresent()) {
            model.addAttribute("progreso", progreso.get());
            model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
            return "progreso/form";
        } else {
            return "redirect:/progreso"; // Si no existe el progreso, redirigir
        }
    }

    @PostMapping("/editar/{id}")
    public String updateProgreso(@PathVariable Long id, @ModelAttribute Progreso progreso) {
        Optional<Progreso> existingProgreso = progresoService.getProgresoById(id);
        if (existingProgreso.isPresent()) {
            Progreso updatedProgreso = existingProgreso.get();
            
            // Verificar si el Estudiante tiene un ID
            if (progreso.getEstudiante() != null && progreso.getEstudiante().getId() != null) {
                updatedProgreso.setEstudiante(progreso.getEstudiante());  // Asociar el estudiante al progreso
            }

            // Mantener el trimestre
            updatedProgreso.setTrimestre(progreso.getTrimestre());

            // Si la nota ha cambiado o es nueva, guardarla
            if (progreso.getNota() != null) {
                if (progreso.getNota().getId() != null) {
                    // Si la Nota ya existe (tiene un ID), no crear una nueva
                    updatedProgreso.setNota(progreso.getNota());  // Mantener la Nota existente
                } else {
                    // Si la Nota no tiene un ID, crearla antes de asignarla al Progreso
                    Nota savedNota = notaService.saveNota(progreso.getNota());  // Guardar nueva Nota
                    updatedProgreso.setNota(savedNota);  // Asignar la nueva Nota al Progreso
                }
            }

            // Guardar los cambios en el Progreso
            progresoService.updateProgreso(updatedProgreso);  // Este método se asegura de actualizar el progreso
        }
        return "redirect:/progreso";  // Redirigir al listado de progresos
    }

    @GetMapping("/eliminar/{id}")
    public String deleteProgreso(@PathVariable Long id) {
        progresoService.deleteProgreso(id);
        return "redirect:/progreso";
    }
}
