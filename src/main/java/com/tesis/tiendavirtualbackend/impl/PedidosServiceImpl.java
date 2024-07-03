package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.dto.PedidosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import com.tesis.tiendavirtualbackend.repository.PedidosRepository;
import com.tesis.tiendavirtualbackend.service.PedidosService;
import com.tesis.tiendavirtualbackend.utils.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Autowired
    private PedidosRepository repository;

    @Override
    public Page<Pedidos> getByPage(PedidosRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());

        Page<Pedidos> response = repository.getByPage(request.getPedidos().getSucursal().getId(), request.getPedidos().getSucursal().getId() == null ? 0l : request.getPedidos().getSucursal().getId(),
                pageable);
        return response;
    }

    @Override
    public PedidosResponseDTO save(Pedidos obj, String type) {
        PedidosResponseDTO responseDTO = new PedidosResponseDTO();
        try {
            repository.save(obj);
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

}
