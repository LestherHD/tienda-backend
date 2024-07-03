package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.bo.Sucursales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
    @Query("SELECT a" +
            "FROM Pedidos a " +
            "WHERE (?1 is null or  ?2 = a.sucursal.id)")
    Page<Pedidos> getByPage(Long idSource, Long id,
                               Pageable pageable);
}
