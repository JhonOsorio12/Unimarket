package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.Activo;
import co.edu.uniquindio.uniMarket.entidades.Categoria;
import co.edu.uniquindio.uniMarket.entidades.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ProductoGetDTO {

    private Integer codigo;

    private Activo estado;

    private LocalDateTime fechaLimite;

    private String nombre;

    private String descripcion;

    private Integer unidades;

    private float precio;

    private Integer codigoVendedor;

    private Map<String, String> imagenes;

    private List<Categoria> categorias;
}
