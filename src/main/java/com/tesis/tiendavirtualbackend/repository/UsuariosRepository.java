package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    Usuarios getById(Long id);

    Usuarios getByUsuario(String usuario);

    Usuarios getByUsuarioOrCorreo(String usuario, String correo);

    @Query("Select new com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO(concat(a.nombres, ' ', a.apellidos), a.usuario, " +
            "a.correo, a.telefono, a.principal, b.nombre) " +
            "from Usuarios a " +
            "left join Sucursales b on a.sucursal = b " +
            "where a.usuario = ?1 or a.correo = ?2")
    UsuariosResponseDTO getInfoByUsuarioOrCorreo(String usuario, String correo);

    Usuarios getByTelefono(String telefono);

    Usuarios getByCorreo(String correo);

//    Consulta derivada
    Usuarios getByUsuarioAndContraseniaOrCorreoAndContrasenia(String usuario, String contrasenia1, String correo, String contrasenia2);

    Usuarios getByPrincipal(String principal);

}
