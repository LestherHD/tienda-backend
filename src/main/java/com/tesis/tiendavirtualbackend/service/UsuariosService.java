package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO;

import java.util.List;

public interface UsuariosService {

    Usuarios getById(Long id);

//    HABILITAR Y DESARROLLAR SOLO SI HUBIERA UN MANTENIMIENTO DE USUARIOS
//    Page<Usuarios> getByPage(UsuariosRequestDTO request);

    Usuarios save(Usuarios obj);

    void delete(Long id);

    String getUniqueValidator(UsuariosRequestDTO requestDTO);

    List<Usuarios> getAll();

    Usuarios getByUsuario(String usuario);

    Usuarios getByUsuarioOrCorreoAndContrasenia(String usuarioOCorreo, String contrasenia);

    Usuarios getByPrincipal();

    UsuariosResponseDTO generarCodigoCambiarContrasenia(UsuariosRequestDTO requestDTO);
}
