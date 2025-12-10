/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ColaboradorImp;
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
import pojo.Colaborador;
import pojo.Conductor;

/**
 *
 * @author citla
 */
@Path("colaborador")
public class ColaboradorWS {
    
    @Path("obtener-colaboradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conductor> obtenerColaboradores(){
        return ColaboradorImp.obtenerColaboradores();
    }
    @Path("obtener-administradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerAdministradores(){
        return ColaboradorImp.obtenerAdministradores();
    }
    @Path("obtener-ejecutivos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerEjecutivos(){
        return ColaboradorImp.obtenerEjecutivos();
    }
    @Path("obtener-conductores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conductor> obtenerConductores(){
        return ColaboradorImp.obtenerConductores();
    }
    
    @Path("registrar-colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarColaborador(String json){
        Gson gson = new Gson();
        try {
            Conductor colaborador = gson.fromJson(json, Conductor.class);
            return ColaboradorImp.registrarColaborador(colaborador);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("actualizar-colaborador")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta actualizarColaborador(String json){
        Gson gson = new Gson();
        try {
            Conductor colaborador = gson.fromJson(json, Conductor.class);
            return ColaboradorImp.actualizarColaborador(colaborador);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("obtener-foto/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerFoto(@PathParam ("idColaborador") Integer idColaborador){
        if (idColaborador != null && idColaborador > 0) {
            return ColaboradorImp.obtenerFoto(idColaborador);
        }
        throw new BadRequestException();
    } 
    
    @Path("subir-foto/{idColaborador}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta subirFoto(@PathParam ("idColaborador") Integer idColaborador, byte[] fotografia){
        if (idColaborador != null && idColaborador > 0) {
            return ColaboradorImp.subirFoto(idColaborador, fotografia);
        }
        throw new BadRequestException();
    }
    
    @Path("buscar-colaborador/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conductor> buscarColaborador(@PathParam ("busqueda") String busqueda){
        if (busqueda != null&& !busqueda.isEmpty()) {
            return ColaboradorImp.buscarColaborador(busqueda);
        }
        throw new BadRequestException();
    }
    
}
