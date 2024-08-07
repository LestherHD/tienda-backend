package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.dto.SucursalesRequestDTO;
import com.tesis.tiendavirtualbackend.dto.SucursalesResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SucursalesService {

    Sucursales getById(Long id);

    Page<Sucursales> getByPage(SucursalesRequestDTO request);

    SucursalesResponseDTO save(Sucursales obj, String type);

    SucursalesResponseDTO delete(Long id);

    List<Sucursales> getAll();

}
