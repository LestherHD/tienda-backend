package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.Sucursales;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SucursalesResponseDTO {

    private Sucursales sucursales;
    private String mensaje;
    private boolean error;

}
