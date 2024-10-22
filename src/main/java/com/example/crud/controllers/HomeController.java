package com.example.crud.controllers;

import com.example.crud.models.Persona;
import com.example.crud.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PersonaService personaService;
    
    // Página principal redirige a la lista de productos publicados
    @GetMapping("/")
    public String index() {
        return "redirect:/ver-productos";  // Redirige a la vista "ver-productos.html"
    }

    // Vista para mostrar las personas (productos) publicadas
    @GetMapping("/ver-productos")
    public String listarPublicados(Model model) {
        List<Persona> personas = personaService.getAllPersonas();
        model.addAttribute("personas", personas);  // Pasa la lista de personas a la vista
        return "ver-productos";  // Retorna la vista "ver-productos.html"
    }

    // Vista para cargar o editar personas (productos)
    @GetMapping("/cargar-producto")
    public String cargarPersona(Model model) {
        model.addAttribute("persona", new Persona());  // Objeto vacío para el formulario de creación
        return "cargar-producto";  // Retorna la vista "cargar-producto.html"
    }

    // Guardar nueva persona (producto) o editar existente
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Persona persona) {
        personaService.savePersona(persona);
        return "redirect:/ver-productos";  // Redirige a la página de productos publicados
    }

    // Cargar persona (producto) existente para edición
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Persona persona = personaService.getPersonaById(id).orElse(null);
        model.addAttribute("persona", persona);  // Objeto persona cargado para edición
        return "cargar-producto";  // Muestra el formulario en "cargar-producto.html"
    }

    // Eliminar persona (producto)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        personaService.deletePersona(id);
        return "redirect:/ver-productos";  // Redirige a la página de productos publicados después de eliminar
    }
}
