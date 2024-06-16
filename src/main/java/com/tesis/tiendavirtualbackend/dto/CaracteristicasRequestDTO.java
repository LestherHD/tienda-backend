package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Caracteristicas;
import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaracteristicasRequestDTO {
    private Caracteristicas caracteristicas;
    private int page;
    private int size;

}
