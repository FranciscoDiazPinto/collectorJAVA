package com.example.crud.controllers;

import com.example.crud.models.Producto;
import com.example.crud.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String index() {
        return "redirect:/ver-productos";
    }

    @GetMapping("/ver-productos")
    public String listarPublicados(Model model) {
        List<Producto> productos = productoService.getAllProductos();
        model.addAttribute("productos", productos);
        return "ver-productos";
    }

    @GetMapping("/cargar-producto")
    public String cargarProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "cargar-producto";
    }

@PostMapping("/guardar")
public String guardar(@ModelAttribute Producto producto) {
    System.out.println("Producto a guardar: " + producto);
    try {
        productoService.saveProducto(producto);
        System.out.println("Producto guardado correctamente.");
    } catch (Exception e) {
        System.out.println("Error al guardar el producto: " + e.getMessage());
        e.printStackTrace(); // Esto imprimirá el stack trace de la excepción
    }
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
