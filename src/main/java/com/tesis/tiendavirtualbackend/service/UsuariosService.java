package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO;

import java.util.List;

public interface UsuariosService {

    Usuarios getById(Long id);

    UsuariosResponseDTO save(Usuarios obj, String type);

    void delete(Long id);

    List<Usuarios> getAll();

    Usuarios getByUsuario(String usuario);

    UsuariosResponseDTO getByUsuarioOrCorreo(String usuario, String correo);

    UsuariosResponseDTO getByInfoUsuarioOrCorreo(String usuario, String correo);

    Usuarios getByUsuarioOrCorreoAndContrasenia(String usuarioOCorreo, String contrasenia);

    Usuarios getByPrincipal();

    UsuariosResponseDTO generarCodigoCambiarContrasenia(UsuariosRequestDTO requestDTO);

    UsuariosResponseDTO generarCodigoConfirmarUsuarioPrincipal(UsuariosRequestDTO requestDTO);

    UsuariosResponseDTO actualizarContrasenia(UsuariosRequestDTO requestDTO);
}
