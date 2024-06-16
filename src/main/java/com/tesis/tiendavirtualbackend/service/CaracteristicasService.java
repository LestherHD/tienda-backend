package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Caracteristicas;
import com.tesis.tiendavirtualbackend.dto.CaracteristicasRequestDTO;
import com.tesis.tiendavirtualbackend.dto.CaracteristicasResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CaracteristicasService {

    Caracteristicas getById(Long id);

    Page<Caracteristicas> getByPage(CaracteristicasRequestDTO request);

    CaracteristicasResponseDTO save(Caracteristicas obj, String type);

    CaracteristicasResponseDTO delete(Long id);

    String getUniqueValidator(CaracteristicasRequestDTO requestDTO);

    List<Caracteristicas> getAll();

}
