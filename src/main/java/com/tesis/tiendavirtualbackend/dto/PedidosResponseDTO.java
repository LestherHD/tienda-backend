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

}
