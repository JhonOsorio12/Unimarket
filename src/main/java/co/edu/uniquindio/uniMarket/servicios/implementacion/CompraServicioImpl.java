package co.edu.uniquindio.uniMarket.servicios.implementacion;

import co.edu.uniquindio.uniMarket.DTO.*;
import co.edu.uniquindio.uniMarket.entidades.Compra;
import co.edu.uniquindio.uniMarket.entidades.DetalleCompra;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.CompraRepo;
import co.edu.uniquindio.uniMarket.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import co.edu.uniquindio.uniMarket.servicios.excepcion.ResourceNotFoundException;
import co.edu.uniquindio.uniMarket.servicios.interfaces.CompraServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uniMarket.servicios.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;

    private final DetalleCompraRepo detalleCompraRepo;

    private ProductoServicio productoServicio;

    private EmailServicio emailServicio;

    private final UsuarioRepo usuarioRepo;

    @Override
    public int crearCompra2(CompraDTO compraDTO) throws Exception {

        Compra compra;
        Usuario usuario;

        try {
            usuario = usuarioRepo.buscarCorreoUsuario(compraDTO.getCodigoUsuario());
            List<DetalleCompraDTO> detalles = compraDTO.getDetalleCompraDTO();

            compra = new Compra();
            compra.setUsuario(usuario);
            compra.setMedioPago(compraDTO.getMetodoPago());
            compra.setFechaCreacion(LocalDateTime.now(ZoneId.of("America/Bogota")));

            float valorTotal = calcularValorTotal(detalles);
            compra.setValorTotal(valorTotal);

            Compra compraGuardada = compraRepo.save(compra);

            for (DetalleCompraDTO detalleCompraDTO : compraDTO.getDetalleCompraDTO()) {

                if (detalleCompraDTO.getCodigoProducto() != usuario.getCodigo()) {

                    DetalleCompra detalleCompra = new DetalleCompra();
                    detalleCompra.setCompraDT(compraGuardada);
                    detalleCompra.setPrecioProducto(detalleCompraDTO.getPrecio());
                    detalleCompra.setUnidades(detalleCompraDTO.getUnidades());

                    detalleCompraRepo.save(detalleCompra);
                }else {
                    throw new ResourceNotFoundException("No puede comprar su propio producto");
                }
            }

            emailServicio.enviarEmail(new EmailDTO("Compra", "Su compra se realizó con éxito, los datos de la compra fueron: "
                    +compra.getCodigo()+" "+ compra.getValorTotal()+" "+compra.getFechaCreacion()+" "+compra.getMedioPago()+" "
                    + compra.getUsuario().getNombre() ,compra.getUsuario().getEmail()));

            emailServicio.enviarEmail(new EmailDTO("Compra", "Su venta se realizó con éxito, los datos de la venta fueron: "
                    +compra.getCodigo()+ compra.getValorTotal()+compra.getFechaCreacion()+compra.getMedioPago(),
                     usuario.getEmail()));

        } catch (Exception e) {
            throw new ResourceNotFoundException("No se pudo realizar la compra");
        }

        return compraRepo.save(compra).getCodigo();
    }

    /*
    @Override
    public Compra crearCompra(CompraDTO compraDTO, Usuario usuario, Producto producto, ArrayList<DetalleCompra> detalleCompra) throws Exception {

        Compra compra;
        if (producto.getVendedor().getCodigo().equals(usuario.getCodigo())) {
            throw new ResourceNotFoundException("Usted es el vendedor no puede comprar sus propios productos");
        } else {
            compra = new Compra();

            Optional<Usuario> usuarioExistente = usuarioRepo.findById(usuario.getCodigo());

            if (usuarioExistente.isEmpty()) {
                throw new ResourceNotFoundException("El usuario no existe");
            }

            Optional<Producto> productoExiste = productoRepo.findById(producto.getCodigo());

            compra.setFechaCreacion(LocalDateTime.now(ZoneId.of("America/Bogota")));
            compra.setUsuario(usuarioServicio.obtener(compraDTO.getCodigoUsuario()));
            compra.setMedioPago(compraDTO.getMetodoPago());

            float valorTotal = calcularValorTotal(detalleCompra);
            compra.setValorTotal(valorTotal);

            compraRepo.save(compra);

            for (DetalleCompra dt : detalleCompra) {

                List<Producto> productoLista = productoRepo.listarProductosUsuario(usuario.getCodigo());

                if (productoExiste.isEmpty()) {
                    throw new ResourceNotFoundException("El producto no existe");
                } else {
                    for (Producto p : productoLista) {
                        dt.setProductoDT(p);
                    }
                }

                dt = new DetalleCompra();
                dt.setCompraDT(compra);
                dt.setPrecioProducto(dt.getPrecioProducto());
                dt.setUnidades(dt.getUnidades());

                detalleCompraRepo.save(dt);
            }

            emailServicio.enviarEmail(new EmailDTO("Compra", "Su compra se realizó con éxito, los datos de la compra fueron: "
                    +compra.getCodigo()+ compra.getValorTotal()+compra.getFechaCreacion()+compra.getMedioPago()
                    + usuario.getNombre(), usuario.getEmail()));

            emailServicio.enviarEmail(new EmailDTO("Compra", "Su venta se realizó con éxito, los datos de la venta fueron: "
                    +compra.getCodigo()+ compra.getValorTotal()+compra.getFechaCreacion()+compra.getMedioPago()
                    + producto.getVendedor().getNombre(), producto.getVendedor().getEmail()));
        }

        return compra;

    }

     */

    @Override
    public List<CompraGetDTO> listarComprasUsuario(Integer codigoUsuario) throws Exception {

        List<Compra> lista = compraRepo.listarCompraUsuario(codigoUsuario);
        List<CompraGetDTO> respuesta = new ArrayList<>();

        for (Compra c : lista){
            respuesta.add(convertir(c));
        }

        return respuesta;
    }

    @Override
    public CompraGetDTO obtenerCompra(int codigoCompra) throws Exception {

        Optional<Compra> compra = compraRepo.findById(codigoCompra);

        if (compra.isEmpty()){
            throw new Exception("El código "+codigoCompra+" no está asociado a ningún producto");
        }

        return convertir(obtener(codigoCompra));
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

    /*
    private Compra convertir(CompraDTO compraDTO){

        Compra compra = new Compra();
        compra.setMedioPago(compraDTO.getMetodoPago());
        compra.setFechaCreacion(LocalDateTime.now());
        //compra.setDetalleCompra();
        return compra;
    }*/

    public Float calcularValorTotal(List<DetalleCompraDTO> detalleCompraDTO){

        float valorTotal = 0.f;

        for (DetalleCompraDTO detalleCompra1 : detalleCompraDTO){
            valorTotal = valorTotal + detalleCompra1.getPrecio() * detalleCompra1.getUnidades();
        }
        return valorTotal;
    }

    public int crearDetalleCompra(DetalleCompra detalleCompra, Compra compra) throws Exception {

        DetalleCompra dc = new DetalleCompra();
        dc.setUnidades(detalleCompra.getUnidades());
        dc.setPrecioProducto(detalleCompra.getPrecioProducto());
        dc.setProductoDT(productoServicio.obtener(detalleCompra.getProductoDT().getCodigo()));
        dc.setCompraDT(compra);

        return detalleCompraRepo.save(detalleCompra).getCodigo();
    }

    public DetalleCompra obtenerDetalleCompra(Integer codigo) throws Exception {

        Optional<DetalleCompra> detalleCompra = detalleCompraRepo.findById(codigo);
        if (detalleCompra.isEmpty()){
            throw new Exception("El código "+codigo+" no está asociado a ningún producto");
        }
        return detalleCompra.get();
    }

}
