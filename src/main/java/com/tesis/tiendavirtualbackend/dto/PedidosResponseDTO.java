package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.Pedidos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidosResponseDTO {

    private Pedidos pedidos;
    private String mensaje;
    private boolean error;


    //Variable para dashboard de ventas por sucursal
    private String nombreSucursal;
    //Variable para dashboard de productos más vendidos
    private String nombreProducto;

    private Double total;


    //Constructor para dashboard de ventas por sucursal
    public PedidosResponseDTO(Double total, String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
        this.total = total;
    }

    //Constructor para dashboard de productos mas vendidos
    public PedidosResponseDTO(String nombreProducto, Double total) {
        this.nombreProducto = nombreProducto;
        this.total = total;
    }
}
