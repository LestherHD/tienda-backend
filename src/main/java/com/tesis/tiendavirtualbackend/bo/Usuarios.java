package com.tesis.tiendavirtualbackend.bo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuarios {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @Column(length = 25)
    private String usuario;
    @Column(length = 35)
    private String contrasenia;
    @Column(length = 60)
    private String nombres;
    @Column(length = 70)
    private String apellidos;
    @Column(length = 60)
    private String correo;
    @Column(length = 8)
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursales sucursal;
    @Column(length = 1)
    private String principal;

}
