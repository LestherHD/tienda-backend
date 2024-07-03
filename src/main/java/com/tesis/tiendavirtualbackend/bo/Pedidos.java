package com.tesis.tiendavirtualbackend.bo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pedidos {


    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @Column(length = 1)
    private String estado;
    @Column(length = 70)
    private String nombres;
    @Column(length = 60)
    private String apellidos;
    @Column(length = 8)
    private String telefono;
    @Column(length = 300)
    private String direccion;
    @Column(length = 2)
    private String departamento;
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursales sucursal;
    @Column(length = 1)
    private String metodoPago;
    @Column
    private Double total;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetallePedido> detallePedido;


}
