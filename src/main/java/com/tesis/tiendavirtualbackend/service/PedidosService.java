package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.dto.PedidosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import org.springframework.data.domain.Page;

public interface PedidosService {

    Page<Pedidos> getByPage(PedidosRequestDTO request);

    PedidosResponseDTO save(Pedidos obj, String type);

}
