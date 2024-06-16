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
    public String getUniqueValidator(TipoProductoRequestDTO requestDTO) {

        if (null != requestDTO && null != requestDTO.getTipoProducto()) {
            TipoProducto tp = repository.getByNombre(requestDTO.getTipoProducto().getNombre());
            if (null != tp && (tp.getId() != requestDTO.getTipoProducto().getId() && tp.getNombre().equals(requestDTO.getTipoProducto().getNombre()))){
                return "Ya existe una sucursal con el mismo nombre registrada en el sistema";
            }
        }
        return null;
    }

    @Override
    public List<TipoProducto> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

}
