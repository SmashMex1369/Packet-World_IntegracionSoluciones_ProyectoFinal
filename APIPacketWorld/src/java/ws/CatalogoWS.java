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
import pojo.Direccion;
import pojo.NoGuia;
import pojo.Rol;


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
    public List<Direccion> obtenerDireccion(@PathParam ("codigoPostal") String codigoPostal){
        if (codigoPostal!=null && !codigoPostal.isEmpty()) {
            return CatalogoImp.obtenerDireccion(codigoPostal);
        }
        throw new BadRequestException();
    }
}
