package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosRequestDTO {
    private String usuario;
    private String correo;
    private String codigo;
    private String contrasenia;
    private Usuarios usuarios;
    private int page;
    private int size;
}
