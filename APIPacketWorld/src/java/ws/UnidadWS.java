package ws;

import com.google.gson.Gson;
import dominio.UnidadImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Conductor;
import pojo.Unidad;

/**
 *
 * @author citla
 */
@Path("unidad")
public class UnidadWS {
    
    @Path("obtener-unidades-disponibles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidadesDisponibles(){
        return UnidadImp.obtenerUnidadesDisponibles();
    }
    
    @Path("obtener-unidades-inactivas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidadesInacctivas(){
        return UnidadImp.obtenerUnidadesInactivas();
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
    
    @Path("dar-baja")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta darBajaUnidad(String json){
        Gson gson= new Gson();
        try {
            Unidad unidad= gson.fromJson(json, Unidad.class);
            return UnidadImp.darBajaUnidad(unidad);
        } catch (Exception e) {
            throw new BadRequestException();
        }               
    }
    
    @Path("buscar-historial/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscarHistorialUnidad(@PathParam("busqueda") String busqueda ){
        if (busqueda!=null && !busqueda.isEmpty()){
            return UnidadImp.buscarHistorialUnidad(busqueda);
        }
        throw new BadRequestException();
    }
    
    @Path("asignar-unidad-conductor")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta asignarUnidadAConductor(
            @FormParam("idConductor") Integer idConductor,
            @FormParam("idUnidad") Integer idUnidad
    ){
        if((idConductor!=null && idConductor>0) && (idUnidad!=null && idUnidad>0)){
            return UnidadImp.asignarUnidadAConductor(idConductor, idUnidad);
        }
        throw new BadRequestException();
    }
    
    @Path("conductores-sin-unidad")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conductor> conductoresSinUnidad(){
        return UnidadImp.conductoresSinUnidad();
    }
    
    @Path("desasignar-conductor/{idUnidad}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta desasignarConductor(@PathParam("idUnidad") Integer idUnidad){
        if(idUnidad!=null && idUnidad>0){
            return UnidadImp.desasignarConductor(idUnidad);
        }
        throw new BadRequestException();
    }
    
    @Path("buscar-conductores/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conductor> buscarConductores(@PathParam("busqueda") String busqueda){
        if(busqueda!=null && !busqueda.isEmpty()){
            return UnidadImp.buscarConductores(busqueda);
        }
        throw new BadRequestException();
    }
    
    @Path("verificar-vin/{VIN}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta noPersonal(@PathParam("VIN") String VIN){
        if(VIN!=null && !VIN.isEmpty()){
            return UnidadImp.verificarVIN(VIN);
        }     
        throw new BadRequestException();     
    } 
}
