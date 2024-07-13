package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

    @Query("SELECT a "+
            "FROM Pedidos a " +
            "WHERE a.id = ?1 " )
    Pedidos getById(Long id);

    @Query("SELECT a " +
            "FROM Pedidos a " +
            "WHERE (?1 is null or  ?2 = a.sucursal.id) " +
            "and (a.estado = ?3) ")
    Page<Pedidos> getByPage(Long idSucursalSource, Long idSucursal,
                            String estado, Pageable pageable);

    @Query("SELECT a " +
            "FROM Pedidos a " +
            "WHERE (?1 is null or  ?2 = a.sucursal.id) " +
            "and ( a.fecha between ?3 and ?4) " +
            "and (a.estado = 'E') ")
    Page<Pedidos> getByFilters(Long idSucursalSource, Long idSucursal,
                            String fechaInicio, String fechaFin, Pageable pageable);
}
