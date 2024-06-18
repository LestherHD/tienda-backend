package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.Productos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductosResponseDTO {

    private Productos producto;
    private String mensaje;
    private boolean error;

}
