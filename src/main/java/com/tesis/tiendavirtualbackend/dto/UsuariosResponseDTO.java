package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.Usuarios;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosResponseDTO {

    private Usuarios usuario;
    private String respuesta;
    private boolean error;
    private boolean confirmado;

    //Sección para data informativa del usuario
    private String nombreCompleto;
    private String nombreUsuario;
    private String correo;
    private String telefono;
    private String principal;
    private String nombreSucursal;

    public UsuariosResponseDTO() {
    }

    public UsuariosResponseDTO(Usuarios usuario, String respuesta, boolean error, boolean confirmado) {
        this.usuario = usuario;
        this.respuesta = respuesta;
        this.error = error;
        this.confirmado = confirmado;
    }

    public UsuariosResponseDTO(String nombreCompleto, String nombreUsuario, String correo,
                               String telefono, String principal, String nombreSucursal) {
        this.nombreCompleto = nombreCompleto;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.telefono = telefono;
        this.principal = principal;
        this.nombreSucursal = nombreSucursal;
    }
}
