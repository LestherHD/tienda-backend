package com.tesis.tiendavirtualbackend.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "productos_favoritos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductosFavoritos {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Productos producto;
    @Column(length = 1)
    private Integer orden;

}
