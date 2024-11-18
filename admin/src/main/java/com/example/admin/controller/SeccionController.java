package com.example.admin.controller;

import com.example.admin.model.Seccion;
import com.example.admin.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/secciones")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    // Mostrar lista de todas las secciones
    @GetMapping
    public String listSecciones(Model model) {
        List<Seccion> secciones = seccionService.getAllSecciones();
        model.addAttribute("secciones", secciones);
        return "seccion/list"; // Vista para listar las secciones
    }

    // Mostrar el detalle de una sección
    @GetMapping("/{id}")
    public String showSeccion(@PathVariable Long id, Model model) {
        Optional<Seccion> seccion = seccionService.getSeccionById(id);
        if (seccion.isPresent()) {
            model.addAttribute("seccion", seccion.get());
            return "seccion/detail"; // Vista con el detalle de la sección
        } else {
            return "redirect:/secciones";
        }
    }

    // Formulario para crear una nueva sección
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("seccion", new Seccion());
        return "seccion/form"; // Vista con formulario de creación
    }

    // Crear una nueva sección
    @PostMapping("/new")
    public String createSeccion(@ModelAttribute Seccion seccion) {
        seccionService.createSeccion(seccion);
        return "redirect:/secciones";
    }
}
