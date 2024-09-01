package com.tesis.tiendavirtualbackend.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "productos_caracteristicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Productos producto;
    @ManyToOne
    @JoinColumn(name = "caracteristica_id")
    private Caracteristicas caracteristica;
    @Column
    private String valor;
    @Column
    private Double precio;

}
