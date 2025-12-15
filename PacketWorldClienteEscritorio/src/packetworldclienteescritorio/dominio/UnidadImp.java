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
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;

/**
 *
 * @author citla
 */
public class UnidadImp {
    
    public static HashMap<String, Object> obtenerUnidadesDisponibles(){
        HashMap<String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "unidad/obtener-unidades-disponibles";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()== HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<Unidad>>(){}.getType();
            List <Unidad> unidades= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, unidades);
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
    
    public static HashMap<String, Object> buscarUnidad(String busqueda){
        HashMap <String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "unidad/buscar/" + busqueda;
        Gson gson= new Gson();
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Type tipoLista= new TypeToken <List<Unidad>>(){}.getType();
            List<Unidad> unidades= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, unidades);
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
    
    public static Respuesta registrar(Unidad unidad){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "unidad/registrar";
        Gson gson= new Gson();
        String parametrosJson= gson.toJson(unidad);
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionBody(URL, Constantes.PETICION_POST, parametrosJson, Constantes.APPLICATION_JSON);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            respuesta= gson.fromJson(respuestaAPI.getContenido(), Respuesta.class);
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
                    respuesta.setMensaje("Lo sentimos hay problemas para obtener la información, porfavor inténtelo más tarde.");
            } 
        }
        return respuesta;
    }
    
    public static Respuesta editar(Unidad unidad){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "unidad/editar";
        Gson gson= new Gson();
        String parametrosJson= gson.toJson(unidad);
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionBody(URL, Constantes.PETICION_PUT, parametrosJson, Constantes.APPLICATION_JSON);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            respuesta= gson.fromJson(respuestaAPI.getContenido(), Respuesta.class);
            
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
    
    public static Respuesta darBajaUnidad(Unidad unidad){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "unidad/dar-baja";
        Gson gson= new Gson();
        String parametrosJson= gson.toJson(unidad);
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionBody(URL, Constantes.PETICION_POST, parametrosJson, Constantes.APPLICATION_JSON);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            respuesta= gson.fromJson(respuestaAPI.getContenido(), Respuesta.class);            
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
                    respuesta.setMensaje("Lo sentimos hay problemas para obtener la información, porfavor inténtelo más tarde.");
            } 
        }
        return respuesta;
    }
}
