package com.example.admin.controller;

import com.example.admin.model.Estudiante;
//import com.example.admin.model.Seccion;
import com.example.admin.service.EstudianteService;
import com.example.admin.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private SeccionService seccionService;  // Para manejar secciones

    // Mostrar lista de estudiantes
    @GetMapping
    public String listEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
        return "estudiante/list";
    }

    // Mostrar formulario para crear un nuevo estudiante
    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("secciones", seccionService.getAllSecciones());  // Agregar lista de secciones
        return "estudiante/form";
    }

    // Crear un nuevo estudiante
    @PostMapping
    public String createEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.saveEstudiante(estudiante);
        return "redirect:/estudiantes"; // Redirige al listado de estudiantes
    }

    // Mostrar formulario para editar un estudiante
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Estudiante> estudiante = estudianteService.getEstudianteById(id);
        if (estudiante.isPresent()) {
            model.addAttribute("estudiante", estudiante.get());
            model.addAttribute("secciones", seccionService.getAllSecciones());  // Agregar lista de secciones
            return "estudiante/form";
        } else {
            return "redirect:/estudiantes"; // Redirige si no se encuentra el estudiante
        }
    }

    // Actualizar un estudiante
    @PostMapping("/editar/{id}")
    public String updateEstudiante(@PathVariable Long id, @ModelAttribute Estudiante estudiante) {
        estudiante.setId(id);  // Asegúrate de que el ID esté presente para la actualización
        estudianteService.saveEstudiante(estudiante);  // Usa el método save para actualizar
        return "redirect:/estudiantes";
    }

    // Eliminar un estudiante
    @GetMapping("/eliminar/{id}")
    public String deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return "redirect:/estudiantes";
    }
}
