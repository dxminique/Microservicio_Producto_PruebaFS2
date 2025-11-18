package com.huertohogar.ms_productos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @GetMapping("/ping")
    public String ping() {
        return "ms-productos OK";
    }
}
