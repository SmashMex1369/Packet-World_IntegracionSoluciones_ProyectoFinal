package ws;

import dominio.CatalogoImp;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.CUS;
import pojo.Cliente;
import pojo.Direccion;
import pojo.NoGuia;
import pojo.Rol;
import pojo.TipoUnidad;


/**
 *
 * @author alex4
 */

@Path("catalogo")
public class CatalogoWS {
    @Path("obtener-noguia-disponibles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NoGuia> obtenerNoGuiaDisponibles(){
        return CatalogoImp.obtenerNoGuiaDisponibles();
    }
    
    @Path("obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRoles(){
        return CatalogoImp.obtenerRoles();
    }
    
    @Path("obtener-sucursales-disponibles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CUS> obtenerSucursalesDisponibles(){
        return CatalogoImp.obtenerSucursalesDisponibles();
    }
    
    @Path("obtener-direccion/{codigoPostal}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Direccion> obtenerDireccion(@PathParam ("codigoPostal") Integer codigoPostal){
        if (codigoPostal!=null && codigoPostal>999) {
            return CatalogoImp.obtenerDireccion(codigoPostal);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-tipo-unidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUnidad> obtenerTiposUnidad(){
        return CatalogoImp.obtenerTiposUnidad();
    }
    
    @Path("obtener-nombres-clientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerNombresClientes(){
        return CatalogoImp.obtenerNombresClientes();
    }
}
