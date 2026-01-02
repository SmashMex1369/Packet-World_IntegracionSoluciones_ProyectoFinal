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
import packetworldclienteescritorio.pojo.CUS;
import packetworldclienteescritorio.pojo.Direccion;
import packetworldclienteescritorio.pojo.EstatusEnvio;
import packetworldclienteescritorio.pojo.NoGuia;
import packetworldclienteescritorio.pojo.NombreCliente;
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.pojo.Rol;
import packetworldclienteescritorio.pojo.TipoUnidad;
import packetworldclienteescritorio.utilidad.Constantes;

/**
 *
 * @author citla
 */
public class CatalogoImp {
    
    public static HashMap<String, Object> obtenerEnviosDisponibles(){
        HashMap<String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "catalogo/obtener-noguia-disponibles";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<NoGuia>>(){}.getType();
            List<NoGuia> envios= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
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

    public static HashMap<String, Object> obtenerTiposUnidad(){
        HashMap <String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "catalogo/obtener-tipo-unidades";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
        Gson gson= new Gson();
        Type tipoLista= new TypeToken<List<TipoUnidad>>(){}.getType();
        List<TipoUnidad> tipos= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
        respuesta.put(Constantes.KEY_ERROR, false);
        respuesta.put(Constantes.KEY_LISTA, tipos);
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

    public static HashMap<String, Object> obtenerColonias(int codigoPostal){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS+"catalogo/obtener-direccion/"+codigoPostal;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<Direccion>>(){}.getType();
            List<Direccion> colonias= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, colonias);
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
    
    public static HashMap<String, Object> obtenerEstatusEnvio(){
        HashMap <String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "catalogo/obtener-estatus-envio";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<EstatusEnvio>>(){}.getType();
            List<EstatusEnvio> estatus= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, estatus);
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
    
    public static HashMap<String, Object> obtenerNombresClientes(){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        String URL = Constantes.URL_WS+"catalogo/obtener-nombres-clientes";
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<NombreCliente>>(){}.getType();
            List<NombreCliente> nombres = gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, nombres);
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
    
    public static HashMap<String, Object> obtenerSucursalesDisponibles(){
        HashMap <String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "catalogo/obtener-sucursales-disponibles";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<CUS>>(){}.getType();
            List<CUS> sucursales= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, sucursales);
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
    
    public static HashMap<String, Object> obtenerRoles(){
        HashMap <String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "catalogo/obtener-roles";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<Rol>>(){}.getType();
            List<Rol> roles= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put(Constantes.KEY_ERROR, false);
            respuesta.put(Constantes.KEY_LISTA, roles);
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
    
    public static Integer obtenerCodigoPostalOrigen(Integer idSucursal){
        Integer codigoPostal = 0;
        String URL = Constantes.URL_WS + "catalogo/obtener-codigo-postal-origen/" + idSucursal;
        RespuestaHTTP respuestaAPI = ConexionAPI.peticionGET(URL);
        if (respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK) {
            codigoPostal = Integer.parseInt(respuestaAPI.getContenido());
        }
        return codigoPostal;
    }
    
}
