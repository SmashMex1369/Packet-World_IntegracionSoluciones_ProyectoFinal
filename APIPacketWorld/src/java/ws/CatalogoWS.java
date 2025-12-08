package ws;

import dominio.CatalogoImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.NoGuia;


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
}
