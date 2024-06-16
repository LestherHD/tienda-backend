package com.tesis.tiendavirtualbackend.bo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "caracteristicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Caracteristicas {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @Column(length = 50)
    private String nombre;

}
