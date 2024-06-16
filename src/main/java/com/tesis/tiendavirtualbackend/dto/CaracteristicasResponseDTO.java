package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.Caracteristicas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristicasResponseDTO {

    private Caracteristicas caracteristicas;
    private String mensaje;
    private boolean error;

}
