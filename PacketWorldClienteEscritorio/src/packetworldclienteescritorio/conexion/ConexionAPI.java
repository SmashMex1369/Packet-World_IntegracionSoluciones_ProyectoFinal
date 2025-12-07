package packetworldclienteescritorio.conexion;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 *
 * @author OmarVX
 */
public class ConexionAPI {
    
    public static RespuestaHTTP peticionGET(String URL){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try{
            URL urlWS = new URL(URL);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlWS.openConnection();
            int codigo = conexionHTTP.getResponseCode();
            if(codigo == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(Utilidades.obtenerContenidoWS(conexionHTTP.getInputStream()));
            }
                respuesta.setCodigo(codigo);    
        }catch(MalformedURLException e){
            respuesta.setCodigo(Constantes.ERROR_URL);
            respuesta.setContenido("Error en la dirección de conexión.");
        }catch(IOException ex){
            respuesta.setCodigo(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        }
        return respuesta;
    }
    
    public static RespuestaHTTP peticionBody(String URL, String metodoHTTP, String parametros, String contentType){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try{
            URL urlWS = new URL(URL);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlWS.openConnection();
            conexionHTTP.setRequestMethod(metodoHTTP);
            conexionHTTP.setRequestProperty("Content-Type", contentType);
            conexionHTTP.setDoOutput(true);
            OutputStream os = conexionHTTP.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigo = conexionHTTP.getResponseCode();
            if(codigo == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(Utilidades.obtenerContenidoWS(conexionHTTP.getInputStream()));
            }
                respuesta.setCodigo(codigo);    
        }catch(MalformedURLException e){
            respuesta.setCodigo(Constantes.ERROR_URL);
            respuesta.setContenido("Error en la dirección de conexión.");
        }catch(IOException ex){
            respuesta.setCodigo(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        }
        return respuesta;
    }
    
    public static RespuestaHTTP peticionSinBody(String URL, String metodoHTTP){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try{
            URL urlWS = new URL(URL);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlWS.openConnection();
            conexionHTTP.setRequestMethod(metodoHTTP);
            int codigo = conexionHTTP.getResponseCode();
            if(codigo == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(Utilidades.obtenerContenidoWS(conexionHTTP.getInputStream()));
            }
                respuesta.setCodigo(codigo);    
        }catch(MalformedURLException e){
            respuesta.setCodigo(Constantes.ERROR_URL);
            respuesta.setContenido("Error en la dirección de conexión.");
        }catch(IOException ex){
            respuesta.setCodigo(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        }
        return respuesta;
    }
    
}
