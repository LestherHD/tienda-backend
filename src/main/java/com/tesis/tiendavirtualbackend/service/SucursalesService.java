package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.dto.SucursalesRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SucursalesService {

    Sucursales getById(Long id);

    Page<Sucursales> getByPage(SucursalesRequestDTO request);

    Sucursales save(Sucursales obj);

    void delete(Long id);

    String getUniqueValidator(SucursalesRequestDTO requestDTO);

    List<Sucursales> getAll();

}
