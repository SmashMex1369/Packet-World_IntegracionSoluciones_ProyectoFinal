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
import packetworldclienteescritorio.pojo.Paquete;
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.utilidad.Constantes;

/**
 *
 * @author citla
 */
public class PaqueteImp {
    
    public static HashMap<String, Object> obtenerTodos(){
        HashMap<String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "paquete/obtener-paquetes";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()== HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<Paquete>>(){}.getType();
            List <Paquete> paquetes= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put("error", false);
            respuesta.put("paquetes", paquetes);
        }else{
            respuesta.put("error", true);
            switch(respuestaAPI.getCodigo()){
                case Constantes.ERROR_URL:
                    respuesta.put("mensaje",Constantes.MSJ_ERROR_URL);
                    break;
                case Constantes.ERROR_PETICION:
                    respuesta.put("mensaje",Constantes.MSJ_ERROR_PETICION);
                    break;
                default:
                    respuesta.put("mensaje","Lo sentimos hay problemas para obtener la información en este momento este momento, porfavor inténtelo más tarde.");
            }  
        }
        return respuesta;
    }
    
    public static Respuesta registrar(Paquete paquete){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "paquete/registrar";
        Gson gson= new Gson();
        String parametrosJson= gson.toJson(paquete);
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionBody(URL, Constantes.PETICION_POST, parametrosJson, Constantes.APPLICATION_JSON);
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
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
    
    public static Respuesta editar(Paquete paquete){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "paquete/editar";
        Gson gson= new Gson();
        String parametrosJson= gson.toJson(paquete);
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionBody(URL, Constantes.PETICION_PUT, parametrosJson, Constantes.APPLICATION_JSON);
        if (respuestaAPI.getCodigo()== HttpURLConnection.HTTP_OK){
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
    
    public static Respuesta eliminar(int idPaquete){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "paquete/eliminar/" + idPaquete;
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionSinBody(URL, Constantes.PETICION_DELETE);
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
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
                    respuesta.setMensaje("Lo sentimos hay problemas para eliminar la información, porfavor inténtelo más tarde.");
            }
        }
        return respuesta;
    }
}
