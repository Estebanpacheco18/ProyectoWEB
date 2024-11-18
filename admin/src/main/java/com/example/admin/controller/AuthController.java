package com.example.admin.controller;

import com.example.admin.model.User;
import com.example.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Página de Login
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";  // Vista de login
    }

    // Página de Registro
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());  // Pasar un objeto vacío de User al formulario
        return "register";  // Vista de registro
    }

    // Registrar el usuario
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user) {
        userRepository.save(user);  // Guardar el usuario en la base de datos
        return "redirect:/login";  // Redirigir al login después de registrarse
    }

    // Procesar el login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        // Buscar al usuario en la base de datos por su nombre de usuario
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            // Guardar el usuario en la sesión
            session.setAttribute("user", foundUser);
            // Redirigir al índice si las credenciales son correctas
            return "redirect:/index";
        } else {
            // Si no es válido, mostrar un mensaje de error en el login
            model.addAttribute("error", "Invalid username or password");
            return "login";  // Volver al formulario de login
        }
    }

    // Página de índice (requiere autenticación manual, ya que no usamos Spring Security)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession session, Model model) {
        // Comprobar si el usuario está autenticado en la sesión
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);  // Pasar el usuario al modelo
            return "index";  // Vista principal después del login
        } else {
            return "redirect:/login";  // Si no está autenticado, redirigir al login
        }
    }

    // Cerrar sesión
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // Eliminar al usuario de la sesión
        session.invalidate();
        // Redirigir al login después de cerrar sesión
        return "redirect:/login";
    }
}