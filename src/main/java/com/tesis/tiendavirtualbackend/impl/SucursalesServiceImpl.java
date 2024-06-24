package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.dto.SucursalesRequestDTO;
import com.tesis.tiendavirtualbackend.dto.SucursalesResponseDTO;
import com.tesis.tiendavirtualbackend.repository.SucursalesRepository;
import com.tesis.tiendavirtualbackend.service.SucursalesService;
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
import java.util.Map;

@Service
public class SucursalesServiceImpl implements SucursalesService {

    @Autowired
    private SucursalesRepository repository;

    @Override
    public Sucursales getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<Sucursales> getByPage(SucursalesRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());

        Page<Sucursales> response = repository.getByPage(request.getSucursales().getId(), request.getSucursales().getId() == null ? 0l : request.getSucursales().getId(),
                request.getSucursales().getNombre(), request.getSucursales().getNombre() == null ? "" : request.getSucursales().getNombre(),
                request.getSucursales().getDepartamento(), request.getSucursales().getDepartamento() == null ? "" : request.getSucursales().getDepartamento(),
                pageable);
        return response;
    }

    @Override
    public SucursalesResponseDTO save(Sucursales obj, String type) {
        SucursalesResponseDTO responseDTO = new SucursalesResponseDTO();
        try {
            repository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("Registro "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            mapExcepciones.put("sucursales.nombre_UNIQUE", "nombre");
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public SucursalesResponseDTO delete(Long id) {
        SucursalesResponseDTO responseDTO = new SucursalesResponseDTO();

        Sucursales obj = getById(id);
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

    @Override
    public List<Sucursales> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

}
