package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Productos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductosRequestDTO {
    private Productos producto;
    private int page;
    private int size;

}
