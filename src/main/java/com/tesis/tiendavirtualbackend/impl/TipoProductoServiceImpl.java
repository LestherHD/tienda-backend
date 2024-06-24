package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import com.tesis.tiendavirtualbackend.dto.TipoProductoRequestDTO;
import com.tesis.tiendavirtualbackend.dto.TipoProductoResponseDTO;
import com.tesis.tiendavirtualbackend.repository.TipoProductoRepository;
import com.tesis.tiendavirtualbackend.service.TipoProductoService;
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
public class TipoProductoServiceImpl implements TipoProductoService {

    @Autowired
    private TipoProductoRepository repository;

    @Override
    public TipoProducto getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<TipoProducto> getByPage(TipoProductoRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());

        Page<TipoProducto> response = repository.getByPage(request.getTipoProducto().getId(), request.getTipoProducto().getId() == null ? 0l : request.getTipoProducto().getId(),
                request.getTipoProducto().getNombre(), request.getTipoProducto().getNombre() == null ? "" : request.getTipoProducto().getNombre(),
                pageable);
        return response;
    }

    @Override
    public TipoProductoResponseDTO save(TipoProducto obj, String type) {
        TipoProductoResponseDTO responseDTO = new TipoProductoResponseDTO();
        try {
            repository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("Registro "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            mapExcepciones.put("tipo_producto.nombre_UNIQUE", "nombre");
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public TipoProductoResponseDTO delete(Long id) {
        TipoProductoResponseDTO responseDTO = new TipoProductoResponseDTO();

        TipoProducto obj = getById(id);
        if (obj != null) {
            try {
                repository.delete(obj);
                responseDTO.setError(false);
                responseDTO.setMensaje("Registro eliminado con éxito");
            } catch (DataIntegrityViolationException ex){
                HashMap<String, String> mapExcepciones = new HashMap<String, String>();
                mapExcepciones.put("fk_producto_tipo_producto_id", "un producto");
                String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
                responseDTO.setError(true);
                responseDTO.setMensaje(exception);
            }
        }

        return responseDTO;
    }

    @Override
    public List<TipoProducto> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

}
