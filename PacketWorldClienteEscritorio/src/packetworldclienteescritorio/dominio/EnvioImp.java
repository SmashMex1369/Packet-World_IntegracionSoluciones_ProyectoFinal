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
import packetworldclienteescritorio.pojo.Envio;
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.utilidad.Constantes;


/**
 *
 * @author alex4
 */

public class EnvioImp {

    public static HashMap<String, Object> obtenerEnvios(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "envio/obtener-envios";
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Envio>>(){}.getType();
            List<Envio> envios = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, envios);
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
    
    public static HashMap<String, Object> buscarEnvio(String noGuia){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS + "envio/buscar-envio/" +noGuia;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        Gson gson = new Gson();
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            Type tipoLista = new TypeToken<List<Envio>>(){}.getType();
            List<Envio> envios = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, envios);
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
    
    public static Respuesta actualizarEstatusEnvio(Envio envio){
        Respuesta respuesta = new Respuesta();
        String URL = Constantes.URL_WS+"envio/actualizar-estatus";
        Gson gson = new Gson();
        String parametrosJson = gson.toJson(envio);
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
                    respuesta.setMensaje("Campos en formato incorrecto");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos, hay problemas para modificar la informacion en este momento");
            }
        }
        return respuesta;
    }
}
