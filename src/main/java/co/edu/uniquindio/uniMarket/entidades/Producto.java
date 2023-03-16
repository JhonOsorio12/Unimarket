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
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Producto implements Serializable {
    @Id
    //autoinclementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    //para dejar el atributo no null y con un tamaño de caracteres
    @Column(nullable = false , length = 100)
    private String nombre;
    private Integer unidades ;

    //para dejar el atributo no null y con un tamaño de caracteres
    @Column(nullable = false , length = 1000)
    private String descripcion;
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

    @ManyToMany
    private List<Usuario> usuarios;

    @ManyToOne
    private Usuario usuario;








}

