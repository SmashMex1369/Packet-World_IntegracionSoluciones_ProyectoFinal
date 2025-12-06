/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetworldclienteescritorio.utilidad;

/**
 *
 * @author OmarVX
 */
public class Constantes {
    
    public static final int ERROR_URL = 1001;
    public static final int ERROR_PETICION = 1002;
    public static final String URL_WS = "http://localhost:8084/APIPacketWorld/api/";
    public static final String MSJ_ERROR_URL = "Lo sentimos, su solicitud no puede ser realizada... Intente más tarde";
    public static final String MSJ_ERROR_PETICION = "Lo sentimos, tenemos problemas de conexion en este momento... Intente más tarde";
    
    // Llaves HashMap
    public static final String KEY_ERROR = "error";
    public static final String KEY_MENSAJE = "mensaje";
    public static final String KEY_LISTA = "lista_valores";
    public static final String KEY_OBJETO = "objeto";
    
    //Peticiones
   public static final String PETICION_GET = "GET";
   public static final String PETICION_POST= "POST";
   public static final String PETICION_PUT = "PUT";
   public static final String PETICION_DELETE = "DELETE";
   

   //ContentType
   public static final String APPLICATION_JSON = "application/json";
   public static final String APPLICATION_FORM = "application/x-www-form-urlencoded";
    
}
