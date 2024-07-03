package com.tesis.tiendavirtualbackend.dto;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidosRequestDTO {
    private Pedidos pedidos;
    private int page;
    private int size;

}
