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


    //Datos para dashboard de ventas por sucursal
    private String nombreSucursal;
    private Double total;

    //Constructor para dashboard de ventas por sucursal
    public PedidosResponseDTO(String nombreSucursal, Double total) {
        this.nombreSucursal = nombreSucursal;
        this.total = total;
    }
}
