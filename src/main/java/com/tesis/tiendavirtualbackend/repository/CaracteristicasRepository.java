package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Caracteristicas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CaracteristicasRepository extends JpaRepository<Caracteristicas, Long> {

    Caracteristicas getById(Long id);

    @Query("SELECT new com.tesis.tiendavirtualbackend.bo.Caracteristicas(a.id, a.nombre) " +
            "FROM Caracteristicas a " +
            "WHERE (?1 is null or CAST(a.id as string) LIKE %?2%) " +
            "and (?3 is null or a.nombre like %?4%) ")
    Page<Caracteristicas> getByPage(Long idSource, Long id, String nombreSource, String nombre,
                                 Pageable pageable);

    Caracteristicas getByNombre(String nombre);

}
