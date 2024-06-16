package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import com.tesis.tiendavirtualbackend.dto.TipoProductoRequestDTO;
import com.tesis.tiendavirtualbackend.dto.TipoProductoResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TipoProductoService {

    TipoProducto getById(Long id);

    Page<TipoProducto> getByPage(TipoProductoRequestDTO request);

    TipoProductoResponseDTO save(TipoProducto obj, String type);

    TipoProductoResponseDTO delete(Long id);

    String getUniqueValidator(TipoProductoRequestDTO requestDTO);

    List<TipoProducto> getAll();

}
