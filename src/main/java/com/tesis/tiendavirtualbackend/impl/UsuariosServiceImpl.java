package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosRequestDTO;
import com.tesis.tiendavirtualbackend.repository.UsuariosRepository;
import com.tesis.tiendavirtualbackend.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    @Override
    public Usuarios getById(Long id) {
        return repository.getById(id);
    }

//    HABILITAR Y MODIFICAR SOLO SI HUBIERA UN MANTENIMIENTO DE USUARIOS
//    @Override
//    public Page<Usuarios> getByPage(UsuariosRequestDTO request) {
//        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());
//
//        Page<Usuarios> response = repository.getByPage(request.getTipoProducto().getId(), request.getTipoProducto().getId() == null ? 0l : request.getTipoProducto().getId(),
//                request.getTipoProducto().getCodigo(), request.getTipoProducto().getCodigo() == null ? "" : request.getTipoProducto().getCodigo(),
//                request.getTipoProducto().getNombre(), request.getTipoProducto().getNombre() == null ? "" : request.getTipoProducto().getNombre(),
//                pageable);
//        return response;
//    }

    @Override
    public Usuarios save(Usuarios obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Long id) {
        Usuarios obj = getById(id);
        if (obj != null) {
            repository.delete(obj);
        }
    }

    //Método para validar campos únicos, devuelve un mensaje por medio del consumo del servicio
    @Override
    public String getUniqueValidator(UsuariosRequestDTO requestDTO) {

        if (null != requestDTO && null != requestDTO.getUsuarios()) {
            Usuarios tp = repository.getByUsuario(requestDTO.getUsuarios().getUsuario());
            if (null != tp && (tp.getId() != requestDTO.getUsuarios().getId())){
                return "El usuario ingresado ya se encuentra registrado en el sistema";
            }
        }
        return null;
    }

    @Override
    public List<Usuarios> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

    @Override
    public Usuarios getByUsuario(String usuario) {
        return repository.getByUsuario(usuario);
    }

    @Override
    public Usuarios getByUsuarioOrCorreoAndContrasenia(String usuarioOCorreo, String contrasenia) {
        return repository.getByUsuarioAndContraseniaOrCorreoAndContrasenia(usuarioOCorreo, contrasenia, usuarioOCorreo, contrasenia);
    }

    @Override
    public Usuarios getByPrincipal() {
        return repository.getByPrincipal("Y");
    }
}
