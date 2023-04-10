package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {
    @Id
    //autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Activo activo;

    @Column(nullable = false)
    private LocalDateTime fechaCreado;

    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    //para dejar el atributo no null y con un tamaño de caracteres
    @Column(nullable = false, length = 100)
    private String nombre;

    //para dejar el atributo no null y con un tamaño de caracteres
    @Column(nullable = false)
    private float precio;

     @Column(nullable = false)
    private Integer unidades ;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagen;

    @ElementCollection
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Categoria> categoria;

    @ManyToMany(mappedBy = "favoritos")
    @ToString.Exclude
    private List<Usuario> usuarios;

    @ManyToOne
    private Usuario vendedor;

    @OneToMany(mappedBy = "productoDT")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "productoPM")
    @ToString.Exclude
    private List<ProductoModerador> productoModerador;

    @OneToMany(mappedBy = "productoCOM")
    @ToString.Exclude
    private List<Comentario> productoComentario;








}

