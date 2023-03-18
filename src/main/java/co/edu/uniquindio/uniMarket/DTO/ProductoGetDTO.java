package co.edu.uniquindio.uniMarket.DTO;

import co.edu.uniquindio.uniMarket.entidades.Categoria;

import java.time.LocalDate;
import java.util.List;

public class ProductoGetDTO {

    private Integer codigo;

    private LocalDate fechaLimite;

    private boolean activo;

    private LocalDate fechaPublicacion;

    private String nombre;

    private String descripcion;

    private Integer unidades;

    private float precio;

    private Integer codigoVendedor;

    private List<String> imagenes;

    private List<Categoria> categorias;
}
