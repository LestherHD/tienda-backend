package com.tesis.tiendavirtualbackend.bo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tipo_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoProducto {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @Column(length = 70)
    private String nombre;
    @Column(length = 160)
    private String descripcion;

}
