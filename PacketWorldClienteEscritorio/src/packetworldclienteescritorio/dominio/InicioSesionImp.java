package packetworldclienteescritorio.dominio;

import com.google.gson.Gson;
import java.net.HttpURLConnection;
import packetworldclienteescritorio.conexion.ConexionAPI;
import packetworldclienteescritorio.dto.RSAutenticacionColaborador;
import packetworldclienteescritorio.pojo.RespuestaHTTP;
import packetworldclienteescritorio.utilidad.Constantes;

/**
 *
 * @author OmarVX
 */
public class InicioSesionImp {
    
    public static RSAutenticacionColaborador verificarCredenciales(String noPersonal, String contraseña){
        RSAutenticacionColaborador respuesta= new RSAutenticacionColaborador();
        String parametros= "noPersonal="+noPersonal+"&contraseña="+contraseña;
        String URL= Constantes.URL_WS + "autenticacion/colaborador";
        RespuestaHTTP respuestaAPI= ConexionAPI.peticionBody(URL, Constantes.PETICION_POST, parametros, Constantes.APPLICATION_FORM);
        if(respuestaAPI.getCodigo()==HttpURLConnection.HTTP_OK){
            try {
                Gson gson= new Gson();
                respuesta= gson.fromJson(respuestaAPI.getContenido(), RSAutenticacionColaborador.class);
            } catch (Exception e) {
                respuesta.setError(false);
                respuesta.setMensaje("Lo sentimos hubo un error al obtener la información, inténtelo más tarde.");
            }
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
                    respuesta.setMensaje("Datos requeridos para poder realizar la operación solicitada.");
                    break;
                default:
                    respuesta.setMensaje("Lo sentimos hay problemas para verificar sus crendenciales en este momento, porfavor inténtelo más tarde.");
            }
        }
        return respuesta;
    }
}
