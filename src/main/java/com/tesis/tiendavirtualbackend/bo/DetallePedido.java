package com.tesis.tiendavirtualbackend.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetallePedido {


    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedidos pedido;
    @Column(length = 1300)
    private String descripcion;
    @Column
    private Long cantidad;
    @Column
    private Double precio;


}
