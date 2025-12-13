package packetworldclienteescritorio.dominio;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import packetworldclienteescritorio.conexion.ConexionAPI;
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;

/**
 *
 * @author citla
 */
public class UnidadImp {
    
    public static HashMap<String, Object> obtenerUnidades(){
        HashMap<String, Object> respuesta= new LinkedHashMap<>();
        String URL= Constantes.URL_WS + "unidad/obtener-unidades-disponibles";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionGET(URL);
        if(respuestaAPI.getCodigo()== HttpURLConnection.HTTP_OK){
            Gson gson= new Gson();
            Type tipoLista= new TypeToken<List<Unidad>>(){}.getType();
            List <Unidad> unidades= gson.fromJson(respuestaAPI.getContenido(), tipoLista);
            respuesta.put("error", false);
            respuesta.put(Constantes.KEY_LISTA, unidades);
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
}
