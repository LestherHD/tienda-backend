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
    @Column(length = 50)
    private String nombre;

}
