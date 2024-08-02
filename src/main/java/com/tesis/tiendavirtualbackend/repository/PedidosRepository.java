package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

    @Query("SELECT a "+
            "FROM Pedidos a " +
            "WHERE a.id = ?1 " )
    Pedidos getById(Long id);

    @Query("SELECT a " +
            "FROM Pedidos a " +
            "WHERE (?1 is null or  ?2 = a.sucursal.id) " +
            "and (a.estado = ?3) " +
            "and ( a.fecha between ?4 and ?5) ")
    Page<Pedidos> getByPage(Long idSucursalSource, Long idSucursal,
                            String estado,
                            String fechaInicio, String fechaFin,
                            Pageable pageable);

    @Query("SELECT a " +
            "FROM Pedidos a " +
            "WHERE (?1 is null or  ?2 = a.sucursal.id) " +
            "and ( a.fecha between ?3 and ?4) " +
            "and (a.estado = 'E') ")
    Page<Pedidos> getByFilters(Long idSucursalSource, Long idSucursal,
                            String fechaInicio, String fechaFin, Pageable pageable);

    @Query("SELECT new com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO(sum(a.total) as total, b.nombre as nombreSucursal)  " +
            "FROM Pedidos a " +
            "LEFT JOIN Sucursales b ON b.id = a.sucursal.id " +
            "WHERE (?1 is null or  ?2 = a.sucursal.id) " +
            "and ( a.fecha between ?3 and ?4) " +
            "and (a.estado = 'E') " +
            "group by nombreSucursal " +
            "order by nombreSucursal ")
    List<PedidosResponseDTO> getInfoBranchSales(Long idSucursalSource, Long idSucursal,
                                                String fechaInicio, String fechaFin);

    @Query("SELECT new com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO(b.nombre as nombreProducto, sum(a.precio * a.cantidad) as total)  " +
            "FROM DetallePedido a " +
            "LEFT JOIN Productos b ON b.id = a.producto.id " +
            "LEFT JOIN Pedidos c ON c.id = a.pedido.id " +
            "WHERE (?1 is null or  ?2 = c.sucursal.id) " +
            "and ( c.fecha between ?3 and ?4) " +
            "and (?5 is null or b.estado = ?6) " +
            "and (c.estado = 'E') " +
            "group by nombreProducto " +
            "order by total desc ")
    List<PedidosResponseDTO> getInfoMostSelledProducts(Long idSucursalSource, Long idSucursal,
                                                String fechaInicio, String fechaFin,
                                               String estadoProductoSource, String estadoProducto);
}
