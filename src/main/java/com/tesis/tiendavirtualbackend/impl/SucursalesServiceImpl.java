package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.dto.SucursalesRequestDTO;
import com.tesis.tiendavirtualbackend.repository.SucursalesRepository;
import com.tesis.tiendavirtualbackend.service.SucursalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Sucursales save(Sucursales obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Long id) {
        Sucursales obj = getById(id);
        if (obj != null) {
            repository.delete(obj);
        }
    }

    //Método para validar campos únicos, devuelve un mensaje por medio del consumo del servicio
    @Override
    public String getUniqueValidator(SucursalesRequestDTO requestDTO) {

        if (null != requestDTO && null != requestDTO.getSucursales()) {
            Sucursales tp = repository.getByNombre(requestDTO.getSucursales().getNombre());
            if (null != tp && (tp.getId() != requestDTO.getSucursales().getId() && tp.getNombre().equals(requestDTO.getSucursales().getNombre()))){
                return "Ya existe una sucursal con el mismo nombre registrada en el sistema";
            }
        }
        return null;
    }

    @Override
    public List<Sucursales> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

}
