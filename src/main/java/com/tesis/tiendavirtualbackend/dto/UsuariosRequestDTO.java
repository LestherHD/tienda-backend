package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuariosRequestDTO {
    private String usuario;
    private String correo;
    private String codigo;
    private String contrasenia;
    private String tipoRecuperacion;
    private Usuarios usuarios;
    private int page;
    private int size;
}
