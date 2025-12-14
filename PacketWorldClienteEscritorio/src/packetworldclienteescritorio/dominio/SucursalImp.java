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
import packetworldclienteescritorio.pojo.Sucursal;
import packetworldclienteescritorio.utilidad.Constantes;

/**
 *
 * @author OmarVX
 */
public class SucursalImp {
    
    public static HashMap<String, Object> obtenerTodos(){
        HashMap<String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "sucursal/obtener-sucursales";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()== HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<Sucursal>>(){}.getType();
            List <Sucursal> sucursales = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put("error", false);
            respuesta.put("sucursales", sucursales);
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
    
    public static Respuesta registrarSucursal(Sucursal sucursal){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "sucursal/registrar-sucursal";
        Gson gson= new Gson();
        String parametrosJson= gson.toJson(sucursal);
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
    
    public static Respuesta editarSucursal(Sucursal sucursal){
        Respuesta respuesta= new Respuesta();
        String URL= Constantes.URL_WS + "sucursal/editar-sucursal";
        Gson gson= new Gson();
        String parametrosJson= gson.toJson(sucursal);
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
    
}
