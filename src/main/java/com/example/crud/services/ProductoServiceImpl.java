package com.example.crud.services;

import com.example.crud.models.Producto;
import com.example.crud.repository.ProductoRepository; // Cambia a ProductoRepository
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repositorio; // Cambia a ProductoRepository

    @Override
    public List<Producto> getAllProductos() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        System.out.println("Guardando producto: " + producto);
        return repositorio.save(producto);
    }


    @Override
    public void deleteProducto(Long id) {
        repositorio.deleteById(id);
    }
}
