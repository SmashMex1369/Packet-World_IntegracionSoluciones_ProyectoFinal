package ws;

import com.google.gson.Gson;
import dominio.PaqueteImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Paquete;

/**
 *
 * @author citla
 */
@Path("paquete")
public class PaqueteWS {
    
    @Path("obtener-paquetes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetes(){
        return PaqueteImp.obtenerPaquetes();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarPaquete(String json){
        Gson gson= new Gson();
        try {
            Paquete paquete= gson.fromJson(json, Paquete.class);
            return PaqueteImp.registrarPaquete(paquete);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editarPaquete(String json){
        Gson gson= new Gson();
        try {
            Paquete paquete= gson.fromJson(json, Paquete.class);
            return PaqueteImp.editarPaquete(paquete);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
    
    @Path("eliminar/#{idPaquete}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarPaquete(@PathParam("idPaquete") Integer idPaquete){
        try {
            return PaqueteImp.eliminarPaquete(idPaquete);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
