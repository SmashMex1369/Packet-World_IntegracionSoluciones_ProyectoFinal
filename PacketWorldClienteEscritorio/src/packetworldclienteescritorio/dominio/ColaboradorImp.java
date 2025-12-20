package packetworldclienteescritorio.dominio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import packetworldclienteescritorio.conexion.ConexionAPI;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.pojo.Conductor;
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.utilidad.Constantes;


/**
 *
 * @author alex4
 */

public class ColaboradorImp {
    
    public static HashMap<String, Object> obtenerConductoresSucursal(Integer idSucursal){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "colaborador/obtener-conductores-sucursal/" + idSucursal;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Conductor>>(){}.getType();
            List<Conductor> conductores = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, conductores);
        }else{
            respuesta.put(Constantes.KEY_ERROR, true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.put(Constantes.KEY_MENSAJE,"Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.put(Constantes.KEY_MENSAJE,"Lo sentimos hay problemas para obtener la información en este momento este momento, porfavor inténtelo más tarde.");
            } 
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> buscarConductoresSucursal(Integer idSucursal, String busqueda){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "colaborador/buscar-conductores-sucursal/" + idSucursal + "/" + busqueda;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Conductor>>(){}.getType();
            List<Conductor> conductores = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, conductores);
        }else{
            respuesta.put(Constantes.KEY_ERROR, true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.put(Constantes.KEY_MENSAJE,"Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.put(Constantes.KEY_MENSAJE,"Lo sentimos hay problemas para obtener la información en este momento este momento, porfavor inténtelo más tarde.");
            } 
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> obtenerColaboradores(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "colaborador/obtener-colaboradores";
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        Gson gson = new Gson();
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Type tipoLista = new TypeToken<List<Conductor>>(){}.getType();
            List<Conductor> colaboradores = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, colaboradores);
        }else{
            respuesta.put(Constantes.KEY_ERROR, true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_PETICION);
                    break;
                default:
                    respuesta.put(Constantes.KEY_MENSAJE,"Lo sentimos hay problemas para obtener la información en este momento este momento, porfavor inténtelo más tarde.");
            }  
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> obtenerColaboradoresRol(Integer idRol){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "colaborador/obtener-colaboradores-rol/" + idRol;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        Gson gson = new Gson();
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Type tipoLista = new TypeToken<List<Conductor>>(){}.getType();
            List<Conductor> colaboradores = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, colaboradores);
        }else{
            respuesta.put(Constantes.KEY_ERROR, true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.put(Constantes.KEY_MENSAJE,"Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.put(Constantes.KEY_MENSAJE,"Lo sentimos hay problemas para obtener la información en este momento este momento, porfavor inténtelo más tarde.");
            }  
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> buscarColaboradores(String busqueda){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "colaborador/buscar-colaborador/" + busqueda;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        Gson gson = new Gson();
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Type tipoLista = new TypeToken<List<Conductor>>(){}.getType();
            List<Conductor> colaboradores = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, colaboradores);
        }else{
            respuesta.put(Constantes.KEY_ERROR, true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.put(Constantes.KEY_MENSAJE,"Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.put(Constantes.KEY_MENSAJE,"Lo sentimos hay problemas para obtener la información en este momento este momento, porfavor inténtelo más tarde.");
            }  
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> buscarColaboradoresRol(Integer idRol, String busqueda){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "colaborador/buscar-colaborador-rol/"+ idRol + "/" + busqueda;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        Gson gson = new Gson();
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Type tipoLista = new TypeToken<List<Conductor>>(){}.getType();
            List<Conductor> colaboradores = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, colaboradores);
        }else{
            respuesta.put(Constantes.KEY_ERROR, true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.put(Constantes.KEY_MENSAJE,"Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.put(Constantes.KEY_MENSAJE,"Lo sentimos hay problemas para obtener la información en este momento este momento, porfavor inténtelo más tarde.");
            }  
        }
        return respuesta;
    }
    
    public static Respuesta registrarColaborador(Colaborador colaborador){
        Respuesta respuesta  = new Respuesta();
        String URL = Constantes.URL_WS + "colaborador/registrar-colaborador";
        Gson gson = new Gson();
        String parametrosJson = gson.toJson(colaborador);
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionBody(URL, Constantes.PETICION_POST, parametrosJson, Constantes.APPLICATION_JSON);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            respuesta = gson.fromJson(respuestaAPI.getContenido(), Respuesta.class);
        }else{
            respuesta.setError(true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.setMensaje("Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hay problemas para registar la información, porfavor inténtelo más tarde.");
            } 
        }
        return respuesta;
    }
    
    public static Respuesta actualizarColaborador(Colaborador colaborador){
        Respuesta respuesta  = new Respuesta();
        String URL = Constantes.URL_WS + "colaborador/actualizar-colaborador";
        Gson gson = new Gson();
        String parametrosJson = gson.toJson(colaborador);
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionBody(URL, Constantes.PETICION_PUT, parametrosJson, Constantes.APPLICATION_JSON);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            respuesta = gson.fromJson(respuestaAPI.getContenido(), Respuesta.class);
        }else{
            respuesta.setError(true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.setMensaje("Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hay problemas para editar la información, porfavor inténtelo más tarde.");
            } 
        }
        return respuesta;
    }
    
    public static Respuesta eliminarColaborador(Integer idColaborador){
        Respuesta respuesta  = new Respuesta();
        String URL = Constantes.URL_WS + "colaborador/eliminar-colaborador/" + idColaborador;
        Gson gson = new Gson();
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionSinBody(URL, Constantes.PETICION_PUT);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            respuesta = gson.fromJson(respuestaAPI.getContenido(), Respuesta.class);
        }else{
            respuesta.setError(true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.setMensaje(Constantes.MSJ_ERROR_PETICION);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    respuesta.setMensaje("Campos en formato incorrecto, verifique la información");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hay problemas para actualizar la información, porfavor inténtelo más tarde.");
            } 
        }
        return respuesta;
    }
    
}
