package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.ProductosFavoritos;
import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.dto.ProductosFavoritosResponseDTO;
import com.tesis.tiendavirtualbackend.dto.SucursalesRequestDTO;
import com.tesis.tiendavirtualbackend.dto.SucursalesResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductosFavoritosService {

    ProductosFavoritosResponseDTO save(ProductosFavoritos obj, String type);

    ProductosFavoritosResponseDTO delete(Long id);

    List<ProductosFavoritos> getAll();

}
