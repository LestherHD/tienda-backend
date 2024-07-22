package com.tesis.tiendavirtualbackend.dto;


import com.tesis.tiendavirtualbackend.bo.ProductosFavoritos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductosFavoritosResponseDTO {

    private ProductosFavoritos productosFavoritos;
    private String mensaje;
    private boolean error;

}
