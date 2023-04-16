package co.edu.uniquindio.uniMarket.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Compra implements Serializable {
    @Id
    //autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //Fecha not null y que se autocrea
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Positive
    @Column(nullable = false)
    private float valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false )
    private MetodoPago medioPago;

    @OneToMany(mappedBy = "compraDT")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompra;

    @ManyToOne
    private Usuario usuario;



}
