package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Caracteristicas;
import com.tesis.tiendavirtualbackend.dto.CaracteristicasRequestDTO;
import com.tesis.tiendavirtualbackend.dto.CaracteristicasResponseDTO;
import com.tesis.tiendavirtualbackend.repository.CaracteristicasRepository;
import com.tesis.tiendavirtualbackend.service.CaracteristicasService;
import com.tesis.tiendavirtualbackend.utils.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CaracteristicasServiceImpl implements CaracteristicasService {

    @Autowired
    private CaracteristicasRepository repository;

    @Override
    public Caracteristicas getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<Caracteristicas> getByPage(CaracteristicasRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());

        Page<Caracteristicas> response = repository.getByPage(request.getCaracteristicas().getId(), request.getCaracteristicas().getId() == null ? 0l : request.getCaracteristicas().getId(),
                request.getCaracteristicas().getNombre(), request.getCaracteristicas().getNombre() == null ? "" : request.getCaracteristicas().getNombre(),
                pageable);
        return response;
    }

    @Override
    public CaracteristicasResponseDTO save(Caracteristicas obj, String type) {
        CaracteristicasResponseDTO responseDTO = new CaracteristicasResponseDTO();
        try {
            repository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("Registro "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            mapExcepciones.put("caracteristicas.nombre_UNIQUE", "nombre");
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public CaracteristicasResponseDTO delete(Long id) {
        CaracteristicasResponseDTO responseDTO = new CaracteristicasResponseDTO();

        Caracteristicas obj = getById(id);
        if (obj != null) {
            try {
                repository.delete(obj);
                responseDTO.setError(false);
                responseDTO.setMensaje("Registro eliminado con éxito");
            } catch (DataIntegrityViolationException ex){
                HashMap<String, String> mapExcepciones = new HashMap<String, String>();
                mapExcepciones.put("fk_usuarios_sucursal_id", "un usuario");
                String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
                responseDTO.setError(true);
                responseDTO.setMensaje(exception);
            }
        }

        return responseDTO;
    }

    //Método para validar campos únicos, devuelve un mensaje por medio del consumo del servicio
    @Override
    public String getUniqueValidator(CaracteristicasRequestDTO requestDTO) {

        if (null != requestDTO && null != requestDTO.getCaracteristicas()) {
            Caracteristicas tp = repository.getByNombre(requestDTO.getCaracteristicas().getNombre());
            if (null != tp && (tp.getId() != requestDTO.getCaracteristicas().getId() && tp.getNombre().equals(requestDTO.getCaracteristicas().getNombre()))){
                return "Ya existe una sucursal con el mismo nombre registrada en el sistema";
            }
        }
        return null;
    }

    @Override
    public List<Caracteristicas> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

}
