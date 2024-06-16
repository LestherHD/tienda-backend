package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Sucursales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SucursalesRepository extends JpaRepository<Sucursales, Long> {

    Sucursales getById(Long id);

    @Query("SELECT new com.tesis.tiendavirtualbackend.bo.Sucursales(s.id, s.nombre, s.descripcion, " +
            "s.departamento) " +
            "FROM Sucursales s " +
            "WHERE (?1 is null or CAST(s.id as string) LIKE %?2%) " +
            "and (?3 is null or s.nombre like %?4%) " +
            "and (?5 is null or s.departamento = ?6)")
    Page<Sucursales> getByPage(Long idSource, Long id, String nombreSource, String nombre,
                               String departamentoSource, String departamento,
                               Pageable pageable);

    Sucursales getByNombre(String nombre);

}
