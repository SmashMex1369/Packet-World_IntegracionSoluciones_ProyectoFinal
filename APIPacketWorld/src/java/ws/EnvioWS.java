package ws;

import com.google.gson.Gson;
import dominio.EnvioImp;
import dto.RSBuscarEnvioWeb;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Envio;


/**
 *
 * @author alex4
 */

@Path("envio")
public class EnvioWS {
    @Path("obtener-envios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerEnvio(){
        return EnvioImp.obtenerEnvios();
    }
    
    @Path("crear-envio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json){
        Gson gson = new Gson();
        try {
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.crearEnvio(envio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("actualizar-envio")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEnvio(String json){
        Gson gson = new Gson();
        try {
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.actualizarEnvio(envio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("actualizar-estatus")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatus(String json){
        Gson gson = new Gson();
        try {
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.actualizarEstatus(envio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("buscar-envio/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> buscarEnvio(@PathParam ("noGuia") String noGuia){
        if (noGuia!=null && !noGuia.isEmpty()) {
            return EnvioImp.buscarEnvio(noGuia);
        }
        throw new BadRequestException();
    }
    
    @Path("buscar-envio-web/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RSBuscarEnvioWeb buscarEnvioWeb(@PathParam ("noGuia") String noGuia){
        if (noGuia!=null && !noGuia.isEmpty()) {
            return EnvioImp.buscarEnvioWeb(noGuia);
        }
        throw new BadRequestException();
    }
    
    @Path("historial-estatus-envio/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> historialEstatusEnvio(@PathParam ("noGuia") String noGuia){
        if (noGuia!=null && !noGuia.isEmpty()) {
            return EnvioImp.historialEstatusEnvio(noGuia);
        }
        throw new BadRequestException();
    }
    
    @Path("verificar-no-guia/{noGuia}")
    @GET
    public Respuesta verificar(@PathParam("noGuia") String noGuia){
        if(noGuia!=null && !noGuia.isEmpty()){
            return EnvioImp.verificarNoGuia(noGuia);
        }
        throw new BadRequestException();
    }
}
