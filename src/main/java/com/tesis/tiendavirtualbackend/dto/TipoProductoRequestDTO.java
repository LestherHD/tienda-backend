package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoProductoRequestDTO {
    private TipoProducto tipoProducto;
    private int page;
    private int size;

}
