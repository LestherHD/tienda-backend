package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    Usuarios getById(Long id);

//    HABILITAR Y MODIFICAR SOLO SI HUBIERA UN MANTENIMIENTO DE USUARIOS, DE LO CONTRARIO NO
//    @Query("SELECT new com.tesis.tiendavirtualbackend.bo.Usuarios(u.id, u.usuario, u.contrasenia, u.nombres, " +
//            "u.apellidos, u.correo, u.telefono) " +
//            "FROM Usuarios u " +
//            "WHERE (?1 is null or CAST(tp.id as string) LIKE %?2%) " +
//            "and (?3 is null or tp.codigo like %?4%) " +
//            "and (?5 is null or tp.nombre like %?6%)")
//    Page<Usuarios> getByPage(Long idSource, Long id, String codigoSource, String codigo, String nombreSource, String nombre, Pageable pageable);

    Usuarios getByUsuario(String usuario);

//    JPQL
//    @Query("Select new com.tesis.tiendavirtualbackend.bo.Usuarios(u.id, u.usuario) " +
//            " from Usuarios u" +
//            " where ?1 = u.usuario" +
//            " and ?2 = u.contrasenia")
//    Usuarios getUsuario(String usuario, String contrasenia);

//    Consulta derivada
    Usuarios getByUsuarioAndContraseniaOrCorreoAndContrasenia(String usuario, String contrasenia1, String correo, String contrasenia2);

    Usuarios getByPrincipal(String principal);
}
