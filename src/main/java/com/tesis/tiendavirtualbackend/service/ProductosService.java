package com.tesis.tiendavirtualbackend.service;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.bo.Productos;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import com.tesis.tiendavirtualbackend.dto.ProductosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.ProductosResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductosService {

    Productos getById(Long id);

    Page<Productos> getByPage(ProductosRequestDTO request);

    Page<Productos> getByFilter(ProductosRequestDTO request);

    ProductosResponseDTO save(Productos obj, String type);

    ProductosResponseDTO delete(Long id);

    List<Productos> getAll();

}
