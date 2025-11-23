package com.huertohogar.ms_productos.controller;

import com.huertohogar.ms_productos.model.Producto;
import com.huertohogar.ms_productos.service.ProductoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // TEST: comprobar si está vivo el microservicio
    @GetMapping("/ping")
    public String ping() {
        return "ms-productos OK";
    }

    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAll();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoService.getById(id);
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoService.create(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto datos) {
        return productoService.update(id, datos);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoService.delete(id);
    }


    @PutMapping("/{id}/descontarStock")
    public ResponseEntity<?> descontarStock(
            @PathVariable Long id,
            @RequestParam int cantidad) {

        productoService.descontarStock(id, cantidad);
        return ResponseEntity.ok("Stock actualizado");
    }
}
