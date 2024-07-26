package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Caracteristicas;
import com.tesis.tiendavirtualbackend.bo.Productos;
import com.tesis.tiendavirtualbackend.bo.TipoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, Long> {

    @Query("SELECT a "+
            "FROM Productos a " +
            "WHERE a.id = ?1 " )
    Productos getById(Long id);

    @Query("SELECT a "+
            "FROM Productos a " +
            "WHERE (?1 is null or CAST(a.id as string) LIKE %?2%) " +
            "and (?3 is null or a.nombre like %?4%) " +
            "and (?5 is null or a.precio >= ?6) " +
            "and (?7 is null or a.precio <= ?8) " +
            "and (?9 is null or a.tipoProducto.id = ?10) " +
            "and (?11 is null or a.estado = ?12) ")
    Page<Productos> getByPage(Long idSource, Long id, String nombreSource, String nombre,
                              Double precioMinSource, Double precioMin,
                              Double precioMaxSource, Double precioMax,
                              Long tipoProductoSource, Long tipoProducto,
                              String estadoSource, String estado,
                              Pageable pageable);

    @Query("SELECT a "+
            "FROM Productos a " +
            "WHERE ((?1 is null or a.nombre like %?2%) " +
            "or (?3 is null or a.descripcion like %?4%)) " +
            "and a.estado = ?5 " +
            "and (?6 is null or a.tipoProducto.id = ?7)")
    Page<Productos> getByFilters(String nombreSource, String nombre,
                                 String descripcionSource, String descripcion,
                                 String estado,
                                 Long tipoProductoSource, Long tipoProducto,
                                 Pageable pageable);

    @Query("SELECT a "+
            "FROM Productos a " +
            "inner join ProductosFavoritos b on a.id = b.producto.id " +
            "where a.id = ?1" )
    Productos findInProductosFavoritos(Long productoId);


    List<Productos> getByEstado(String estado, Sort sort);

}
