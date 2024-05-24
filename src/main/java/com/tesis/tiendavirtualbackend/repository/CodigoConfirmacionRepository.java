package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.CodigoConfirmacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodigoConfirmacionRepository extends JpaRepository<CodigoConfirmacion, Long> {

    CodigoConfirmacion getById(Long id);

    CodigoConfirmacion getByUsuarioIdAndTipo(Long usuarioId, String tipo);

    List<CodigoConfirmacion> getByUsuarioId(Long usuarioId);

    CodigoConfirmacion getByCodigoAndUsuarioIdAndTipo(String codigo, Long id, String tipo);
}
