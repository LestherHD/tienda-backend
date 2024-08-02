package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.dto.PedidosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PedidosService {

    Pedidos getById(Long id);

    Page<Pedidos> getByPage(PedidosRequestDTO request);

    PedidosResponseDTO save(Pedidos obj, String type);

    PedidosResponseDTO delete(Long id);

    List<Pedidos> getAll();

    Page<Pedidos> getByFilter(PedidosRequestDTO request);

    List<PedidosResponseDTO> getInfoBranchSales(PedidosRequestDTO request);

    List<PedidosResponseDTO> getInfoMostSelledProducts(PedidosRequestDTO request);
}
