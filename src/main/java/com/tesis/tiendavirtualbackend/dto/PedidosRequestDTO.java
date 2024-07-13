package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PedidosRequestDTO {
    private Pedidos pedidos;
    private Date fechaInicio;
    private Date fechaFin;
    private int page;
    private int size;

}
