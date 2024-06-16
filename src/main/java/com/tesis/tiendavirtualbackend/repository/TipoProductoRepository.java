package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    TipoProducto getById(Long id);

    @Query("SELECT new com.tesis.tiendavirtualbackend.bo.TipoProducto(a.id, a.nombre, a.descripcion) " +
            "FROM TipoProducto a " +
            "WHERE (?1 is null or CAST(a.id as string) LIKE %?2%) " +
            "and (?3 is null or a.nombre like %?4%) ")
    Page<TipoProducto> getByPage(Long idSource, Long id, String nombreSource, String nombre,
                               Pageable pageable);

    TipoProducto getByNombre(String nombre);

}
