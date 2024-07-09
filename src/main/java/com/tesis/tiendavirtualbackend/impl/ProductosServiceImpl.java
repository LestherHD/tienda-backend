package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.bo.Productos;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import com.tesis.tiendavirtualbackend.dto.ProductosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.ProductosResponseDTO;
import com.tesis.tiendavirtualbackend.repository.PedidosRepository;
import com.tesis.tiendavirtualbackend.repository.ProductosRepository;
import com.tesis.tiendavirtualbackend.service.ProductosService;
import com.tesis.tiendavirtualbackend.utils.GlobalExceptionHandler;
import com.tesis.tiendavirtualbackend.utils.MetodosUtils;
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
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository repository;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Override
    public Productos getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<Productos> getByPage(ProductosRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());
        Page<Productos> response = repository.getByPage(request.getProducto().getId(), request.getProducto().getId() == null ? 0l : request.getProducto().getId(),
                request.getProducto().getNombre(), request.getProducto().getNombre() == null ? "" : request.getProducto().getNombre(),
                request.getProducto().getRangoPrecioInicio(), request.getProducto().getRangoPrecioInicio() == null ? 0d : request.getProducto().getRangoPrecioInicio(),
                request.getProducto().getRangoPrecioFin(), request.getProducto().getRangoPrecioFin() == null ? 0d : request.getProducto().getRangoPrecioFin(),
                request.getProducto().getTipoProducto() != null ? request.getProducto().getTipoProducto().getId() : null, request.getProducto().getTipoProducto() == null ? null : request.getProducto().getTipoProducto().getId(),
                pageable);

        if (response != null && response.getContent() != null && response.getContent().size() > 0){
            for (Productos obj: response.getContent()){
                obj.setImageSrc(MetodosUtils.encodeBase64String(obj.getImagen()));
            }
        }

        return response;
    }

    @Override
    public Page<Productos> getByFilter(ProductosRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());
        Page<Productos> response = repository.getByFilters(
                request.getProducto().getNombre(), request.getProducto().getNombre() == null ? "" : request.getProducto().getNombre(),
                request.getProducto().getNombre(), request.getProducto().getNombre() == null ? "" : request.getProducto().getNombre(),
                pageable);

        if (response != null && response.getContent() != null && response.getContent().size() > 0){
            for (Productos obj: response.getContent()){
                obj.setImageSrc(MetodosUtils.encodeBase64String(obj.getImagen()));
            }
        }

        return response;
    }

    @Override
    public ProductosResponseDTO save(Productos obj, String type) {
        ProductosResponseDTO responseDTO = new ProductosResponseDTO();
        try {
            obj.setImagen(MetodosUtils.decodeBase64String(obj.getImageSrc()));
            repository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("Registro "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            mapExcepciones.put("productos.nombre_UNIQUE", "nombre");
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public PedidosResponseDTO savePedidos(Pedidos obj, String type) {
        PedidosResponseDTO responseDTO = new PedidosResponseDTO();
        try {
            pedidosRepository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("Registro "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public ProductosResponseDTO delete(Long id) {
        ProductosResponseDTO responseDTO = new ProductosResponseDTO();

        Productos obj = getById(id);
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
    public List<Productos> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

}
