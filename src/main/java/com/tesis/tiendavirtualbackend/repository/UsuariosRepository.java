package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT new com.tesis.tiendavirtualbackend.bo.Usuarios(a.id, a.usuario, a.contrasenia, a.nombres, a.apellidos, a.correo, " +
            "a.telefono, b, a.principal) " +
            "FROM Usuarios a " +
            "LEFT JOIN Sucursales b on b.id = a.sucursal.id " +
            "WHERE (?1 is null or CAST(a.id as string) LIKE %?2%) " +
            "and (?3 is null or a.nombres like %?4%) " +
            "and (?5 is null or a.apellidos like %?6%) " +
            "and (?7 is null or a.usuario like %?8%) " +
            "and (?9 is null or a.correo like %?10%) " +
            "and (?11 is null or a.sucursal.id = ?12) " +
            "and (?13 is null or a.principal like %?14%) "+
            "and (?15 is null or a.telefono like %?16%) ")
    Page<Usuarios> getByPage(Long idSource, Long id,
                             String nombresSource, String nombres,
                             String apellidosSource, String apellidos,
                             String usuarioSource, String usuario,
                             String correoSource, String correo,
                             Long sucursalSource, Long sucursal,
                             String principalSource, String principal,
                             String telefonoSource, String telefono,
                             Pageable pageable);

}
