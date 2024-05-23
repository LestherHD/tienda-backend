package com.tesis.tiendavirtualbackend.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "codigo_confirmacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CodigoConfirmacion {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @Column(length = 15)
    private String codigo;
    @Column(length = 1)
    private String tipo;
    @Column()
    private Long usuarioId;
    @Column()
    private Date fecha;


}
