package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.admin.model.Actividad;
import com.example.admin.service.ActividadService;
import com.example.admin.service.SeccionService;

import java.util.*;

@Controller
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private SeccionService seccionService;  // Para manejar secciones

    // Mostrar lista de actividades
    @GetMapping
    public String listActividades(Model model) {
        List<Actividad> actividades = actividadService.getAllActividades();
        model.addAttribute("actividades", actividades);
        return "actividad/list";
    }

    // Mostrar formulario para crear una nueva actividad
    @GetMapping("/nueva")
    public String showCreateForm(Model model) {
        model.addAttribute("actividad", new Actividad());
        model.addAttribute("secciones", seccionService.getAllSecciones());  // Agregar lista de secciones
        return "actividad/form";
    }

    // Crear una nueva actividad
    @PostMapping
    public String createActividad(@ModelAttribute Actividad actividad) {
        actividadService.createActividad(actividad);
        return "redirect:/actividades"; // Redirige al listado
    }

    // Mostrar formulario para editar una actividad
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Actividad> actividad = actividadService.getActividadById(id);
        if (actividad.isPresent()) {
            model.addAttribute("actividad", actividad.get());
            model.addAttribute("secciones", seccionService.getAllSecciones());  // Agregar lista de secciones
            return "actividad/form";
        } else {
            return "redirect:/actividades"; // Redirige si no se encuentra la actividad
        }
    }

    // Actualizar una actividad
    @PostMapping("/editar/{id}")
    public String updateActividad(@PathVariable Long id, @ModelAttribute Actividad actividad) {
        actividad.setId(id);  // Asegurar que el ID de la actividad esté presente para la actualización
        actividadService.updateActividad(id, actividad);
        return "redirect:/actividades";
    }

    // Eliminar una actividad
    @GetMapping("/eliminar/{id}")
    public String deleteActividad(@PathVariable Long id) {
        actividadService.deleteActividad(id);
        return "redirect:/actividades";
    }
}
