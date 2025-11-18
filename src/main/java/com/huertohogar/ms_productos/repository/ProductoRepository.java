package com.huertohogar.ms_productos.repository;
import com.huertohogar.ms_productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoRepository extends JpaRepository<Producto, Long>{
}
