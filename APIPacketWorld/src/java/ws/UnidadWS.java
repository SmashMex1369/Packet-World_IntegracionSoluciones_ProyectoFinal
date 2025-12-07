package ws;

import com.google.gson.Gson;
import dominio.UnidadImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pojo.Unidad;

/**
 *
 * @author citla
 */
@Path("unidad")
public class UnidadWS {
    
    @Path("obtener-unidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidades(){
        return UnidadImp.obtenerUnidades();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarUnidad(String json){
        Gson gson= new Gson();
        try {
            Unidad unidad= gson.fromJson(json, Unidad.class);
            return UnidadImp.registrarUnidad(unidad);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }         
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editarUnidad(String json){
        Gson gson= new Gson();
        try {
            Unidad unidad= gson.fromJson(json, Unidad.class);
            return UnidadImp.editarUnidad(unidad);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("buscar/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscarUnidad(@PathParam("busqueda") String busqueda ){
        if (busqueda!=null && !busqueda.isEmpty()){
            return UnidadImp.buscarUnidad(busqueda);
        }
        throw new BadRequestException();
    }
    
}
