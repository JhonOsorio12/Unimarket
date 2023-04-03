package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.CompraDTO;
import co.edu.uniquindio.uniMarket.DTO.CompraGetDTO;
import co.edu.uniquindio.uniMarket.DTO.DetalleCompraDTO;
import co.edu.uniquindio.uniMarket.DTO.DetalleCompraGetDTO;
import co.edu.uniquindio.uniMarket.entidades.Compra;
import co.edu.uniquindio.uniMarket.entidades.DetalleCompra;
import co.edu.uniquindio.uniMarket.repositorios.CompraRepo;
import co.edu.uniquindio.uniMarket.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CompraServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;

    private final DetalleCompraRepo detalleCompraRepo;

    private UsuarioServicio usuarioServicio;

    private ProductoServicio productoServicio;

    @Override
    public int crearCompra(CompraDTO compraDTO) throws Exception{

       Compra compra = new Compra();
        compra.setMedioPago(compraDTO.getMetodoPago());
        compra.setUsuario(usuarioServicio.obtener(compraDTO.getCodigoUsuario()));
        compra.setFechaCreacion(LocalDateTime.now());

        return compraRepo.save(compra).getCodigo();

    }

    @Override
    public List<CompraGetDTO> listarComprasUsuario(Integer codigoUsuario) {

        List<Compra> lista = compraRepo.listarCompraUsuario(codigoUsuario);
        List<CompraGetDTO> respuesta = new ArrayList<>();

        for (Compra c : lista){
            respuesta.add(convertir(c));
        }

        return respuesta;
    }

    @Override
    public Compra obtener(Integer codigoCompra) throws Exception {

        Optional<Compra> compra = compraRepo.findById(codigoCompra);

        if (compra.isEmpty()){
            throw new Exception("El código "+codigoCompra+" no está asociado a ninguna compra");
        }

        return compra.get();
    }

    private CompraGetDTO convertir(Compra compra, DetalleCompraDTO detalleCompraDTO){

        CompraGetDTO compraDTO = new CompraGetDTO(
                compra.getCodigo(),
                compra.getFechaCreacion(),
                compra.getValorTotal(),
                compra.getMedioPago(),
                compra.getUsuario().getCodigo(),
                new ArrayList<>()
        );
        return null;
    }

    private CompraGetDTO convertir(Compra compra){

        CompraGetDTO compraDTO = new CompraGetDTO(
                compra.getCodigo(),
                compra.getFechaCreacion(),
                compra.getValorTotal(),
                compra.getMedioPago(),
                compra.getUsuario().getCodigo(),
                convertir( compra.getDetalleCompra() )
        );
        return compraDTO;

    }

    private List<DetalleCompraDTO> convertir(List<DetalleCompra> dc){
        return new ArrayList<>();
    }

    private Compra convertir(CompraDTO compraDTO){

        Compra compra = new Compra();
        compra.setMedioPago(compraDTO.getMetodoPago());
        compra.setFechaCreacion(LocalDateTime.now());
        //compra.setDetalleCompra();
        return compra;
    }

    public Float calcularValorTotal(List<DetalleCompra> detalleCompra, List<Compra> compraT){

        Float valorTotal = 0.f;

        for (DetalleCompra detalleCompra1 : detalleCompra){
            valorTotal = valorTotal + detalleCompra1.getPrecioProducto() * detalleCompra1.getUnidades();
        }
        for (Compra compra1 : compraT){
            valorTotal = valorTotal + compra1.getValorTotal();
        }
        return valorTotal;
    }

    public int crearDetalleCompra(DetalleCompraDTO detalleCompraDTO, Compra compra) throws Exception {

        DetalleCompra detalleCompra = new DetalleCompra();
        detalleCompra.setUnidades(detalleCompraDTO.getUnidades());
        detalleCompra.setPrecioProducto(detalleCompraDTO.getValor());
        detalleCompra.setProductoDT(productoServicio.obtener(detalleCompraDTO.getCodigoProducto()));
        detalleCompra.setCompraDT(compra);

        return detalleCompraRepo.save(detalleCompra).getCodigo();
    }

    public List<DetalleCompraGetDTO> listarDetalleCompra(Integer codigoCompra) {

        List<DetalleCompra> lista = detalleCompraRepo.listarDetalleCompra(codigoCompra);
        List<DetalleCompraGetDTO> respuesta = new ArrayList<>();

        for (DetalleCompra dt : lista){
            //respuesta.add(convertir());
        }

        return respuesta;
    }

    public DetalleCompra obtenerDetalleCompra(Integer codigo) throws Exception {

        Optional<DetalleCompra> detalleCompra = detalleCompraRepo.findById(codigo);
        if (detalleCompra.isEmpty()){
            throw new Exception("El código "+codigo+" no está asociado a ningún producto");
        }

        return detalleCompra.get();
    }

}
