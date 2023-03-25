package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ProductoDTO {

    private String nombre;

    private String descripcion;

    private Integer unidades;

    private float precio;

    private Integer codigoVendedor;

    private Map<String, String> imagenes;

    private List<Categoria> categorias;

}
