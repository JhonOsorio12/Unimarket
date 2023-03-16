package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Producto implements Serializable {
    @Id
    //autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //para dejar el atributo no null y con un tamaño de caracteres
    @Column(nullable = false , length = 100)
    private String nombre;

     @Column(nullable = false)
    private Integer unidades ;

    //para dejar el atributo no null y con un tamaño de caracteres
    @Column(nullable = false , length = 1000)
    private String descripcion;
    @Column(nullable = false)
    private float precio;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private LocalDate fechaCreado;

    @Column(nullable = false)
    private LocalDate fechaLimite;
    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagen;
    @ElementCollection
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Categoria> categoria;

    @ManyToMany(mappedBy = "favoritos")
    private List<Usuario> usuarios;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "productoDT")
    private List<Detalle_Compra> detalleCompras;

    @OneToMany(mappedBy = "productoPM")
    private List<Producto_Moderador> productoModerador;

    @OneToMany(mappedBy = "productoCOM")
    private List<Comentario> productoComentario;








}

