package com.huertohogar.ms_productos.controller;

import com.huertohogar.ms_productos.model.Producto;
import com.huertohogar.ms_productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "producto-controller",
        description = "Operaciones para gestionar productos del catálogo"
)
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(
            summary = "Comprobar estado del microservicio de productos",
            description = "Devuelve un mensaje simple para verificar que el microservicio está operativo."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Microservicio respondiendo correctamente")
    })
    @GetMapping("/ping")
    public String ping() {
        return "ms-productos OK";
    }

    @Operation(
            summary = "Listar todos los productos",
            description = "Obtiene la lista completa de productos registrados en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    })
    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAll();
    }

    @Operation(
            summary = "Obtener producto por ID",
            description = "Devuelve la información de un producto específico según su identificador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public Producto getById(
            @Parameter(description = "ID del producto a buscar", example = "1")
            @PathVariable Long id
    ) {
        return productoService.getById(id);
    }

    @Operation(
            summary = "Crear un nuevo producto",
            description = "Registra un nuevo producto en el catálogo."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente")
    })
    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoService.create(producto);
    }

    @Operation(
            summary = "Actualizar un producto",
            description = "Actualiza los datos de un producto existente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public Producto update(
            @Parameter(description = "ID del producto a actualizar", example = "1")
            @PathVariable Long id,
            @RequestBody Producto datos
    ) {
        return productoService.update(id, datos);
    }

    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina un producto del catálogo según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID del producto a eliminar", example = "1")
            @PathVariable Long id
    ) {
        productoService.delete(id);
    }

    @Operation(
            summary = "Descontar stock de un producto",
            description = "Disminuye el stock de un producto según la cantidad indicada."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Cantidad inválida"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}/descontarStock")
    public ResponseEntity<?> descontarStock(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Cantidad a descontar del stock", example = "3")
            @RequestParam int cantidad
    ) {
        productoService.descontarStock(id, cantidad);
        return ResponseEntity.ok("Stock actualizado");
    }
}
