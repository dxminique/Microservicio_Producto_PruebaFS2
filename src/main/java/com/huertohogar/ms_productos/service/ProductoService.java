package com.huertohogar.ms_productos.service;

import com.huertohogar.ms_productos.model.Producto;
import com.huertohogar.ms_productos.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto datos) {
        Producto existente = getById(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        existente.setActivo(datos.getActivo());
        return productoRepository.save(existente);
    }

    public void delete(Long id) {
        Producto existente = getById(id);
        productoRepository.delete(existente);
    }


    public void descontarStock(Long id, int cantidad) {
        Producto producto = getById(id);

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para el producto ID " + id);
        }

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);
    }
}
