package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.Usuarios;

public class UsuariosResponseDTO {

    private Usuarios usuario;
    private String respuesta;
    private boolean error;
    private boolean confirmado;

    public UsuariosResponseDTO() {
    }

    public UsuariosResponseDTO(Usuarios usuario, String respuesta, boolean error, boolean confirmado) {
        this.usuario = usuario;
        this.respuesta = respuesta;
        this.error = error;
        this.confirmado = confirmado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
}
