package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoProductoResponseDTO {

    private TipoProducto tipoProducto;
    private String mensaje;
    private boolean error;

}
