package com.tesis.tiendavirtualbackend.bo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "sucursales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sucursales {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @Column(length = 70)
    private String nombre;
    @Column(length = 160)
    private String descripcion;
    @Column(length = 2)
    private String departamento;

}
