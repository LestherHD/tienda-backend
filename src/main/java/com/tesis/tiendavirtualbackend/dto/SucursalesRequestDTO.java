package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalesRequestDTO {
    private Sucursales sucursales;
    private int page;
    private int size;


}
