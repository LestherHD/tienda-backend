package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PedidosRequestDTO {
    private Pedidos pedidos;
    private String fechaInicio;
    private String fechaFin;
    private Long idSucursal;
    private String estadoProducto;
    private int page;
    private int size;

}
