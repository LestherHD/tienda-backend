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

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Autowired
    private PedidosRepository repository;

    @Override
    public Pedidos getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<Pedidos> getByPage(PedidosRequestDTO request) {

        Instant instantInicio = Instant.parse(request.getFechaInicio());
        Instant instantFin = Instant.parse(request.getFechaFin());

        LocalDate localDateInicio = instantInicio.atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate localDateFin = instantFin.atZone(ZoneId.of("UTC")).toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaInicio = localDateInicio.format(formatter) + " 00:00:00";
        String fechaFin = localDateFin.format(formatter)+ " 23:59:59";

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("fecha").descending());
        Page<Pedidos> response = repository.getByPage(request.getPedidos().getSucursal() != null ? request.getPedidos().getSucursal().getId() : null, request.getPedidos().getSucursal() == null ? 0l : request.getPedidos().getSucursal().getId(),
                request.getPedidos().getEstado(), fechaInicio, fechaFin, pageable);
        return response;
    }

    @Override
    public Page<Pedidos> getByFilter(PedidosRequestDTO request) {

        SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
        sDF.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));

        String fechaInicio = sDF.format(request.getFechaInicio());
        String fechaFin = sDF.format(request.getFechaFin());

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("fecha").descending());
        Page<Pedidos> response = repository.getByFilters(request.getPedidos().getSucursal() != null ? request.getPedidos().getSucursal().getId() : null, request.getPedidos().getSucursal() == null ? 0l : request.getPedidos().getSucursal().getId(),
                fechaInicio, fechaFin, pageable);
        return response;
    }

    @Override
    public List<PedidosResponseDTO> getInfoBranchSales(PedidosRequestDTO request) {

        Instant instantInicio = Instant.parse(request.getFechaInicio());
        Instant instantFin = Instant.parse(request.getFechaFin());

        LocalDate localDateInicio = instantInicio.atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate localDateFin = instantFin.atZone(ZoneId.of("UTC")).toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaInicio = localDateInicio.format(formatter) + " 00:00:00";
        String fechaFin = localDateFin.format(formatter)+ " 23:59:59";

        List<PedidosResponseDTO> list = repository.getInfoBranchSales(request.getIdSucursal(), request.getIdSucursal() == null ? 0l : request.getIdSucursal(),
        fechaInicio, fechaFin);
        return list;
    }

    @Override
    public PedidosResponseDTO save(Pedidos obj, String type) {
        PedidosResponseDTO responseDTO = new PedidosResponseDTO();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            obj.setFecha(simpleDateFormat.format(new Date()));
            repository.save(obj);
            responseDTO.setError(false);
            if (type.equals("A")){
                responseDTO.setMensaje("Compra realizada con éxito");
            } else {
                String estado = obj.getEstado().equals("N") ? "Nuevo" :
                        obj.getEstado().equals("P") ? "En proceso" :
                                obj.getEstado().equals("E") ? "Entregado" : "";
                responseDTO.setMensaje("Pedido actualizado con éxito, estado actual: " + estado);
            }

        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public PedidosResponseDTO delete(Long id) {
        PedidosResponseDTO responseDTO = new PedidosResponseDTO();

        Pedidos obj = getById(id);
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
    public List<Pedidos> getAll() {
        return repository.findAll(Sort.by("nombres").ascending());
    }


}
