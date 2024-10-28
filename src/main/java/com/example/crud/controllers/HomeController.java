package com.example.crud.controllers;

import com.example.crud.models.Producto;
import com.example.crud.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000") // Cambia si tu frontend está en otro puerto
@Controller



public class HomeController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String index() {
        return "redirect:/ver-productos";
    }

    @GetMapping("/ver-productos")
    @ResponseBody
    public List<Producto> listarPublicados() {
        return productoService.getAllProductos();
    }

    @GetMapping("/cargar-producto")
    public String cargarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "cargar-producto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.saveProducto(producto);
        return "redirect:/ver-productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoById(id).orElse(null);
        model.addAttribute("producto", producto);
        return "cargar-producto";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return "redirect:/ver-productos";
    }
}
