package com.tesis.tiendavirtualbackend.bo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Productos {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Long id;
    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String descripcion;
    @Column
    private Double precio;
    @Lob
    @Column
    private byte[] imagen;
    @ManyToOne
    @JoinColumn(name = "tipo_producto_id", referencedColumnName = "id")
    private TipoProducto tipoProducto;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ProductoCaracteristica> caracteristicas;

    //Solo para filtro de precio
    @Transient
    private Double rangoPrecioInicio;
    @Transient
    private Double rangoPrecioFin;

    //Para guardar imagen
    @Transient
    private String imageSrc;

    public Productos(Long id, String nombre, String descripcion, Double precio, byte[] imagen,
                     TipoProducto tipoProducto, Set<ProductoCaracteristica> caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.tipoProducto = tipoProducto;
        this.caracteristicas = caracteristicas;
    }
}
