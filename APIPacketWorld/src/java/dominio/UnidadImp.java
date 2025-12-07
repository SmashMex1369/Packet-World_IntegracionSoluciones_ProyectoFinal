package dominio;

import dto.Respuesta;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.ws.rs.BadRequestException;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Unidad;

/**
 *
 * @author citla
 */
public class UnidadImp {
    
    public static List<Unidad> obtenerUnidades(){
        List<Unidad> unidades=null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                unidades= conexionBD.selectList("unidad.obtener-unidades");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static Respuesta registrarUnidad(Unidad unidad){
        Respuesta respuesta= new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if (conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.insert("unidad.registrar", unidad);
                conexionBD.commit();
                if (filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("La unidad ha sido registrada correctamente.");
                }else{
                    respuesta.setMensaje("Lo sentimos, la unidad no pudo ser registrada.");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexión a la BD.");
        }
        return respuesta;
    }
    
    public static Respuesta editarUnidad(Unidad unidad){
        Respuesta respuesta= new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("unidad.editar", unidad);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("La unidad ha sido actualizada correctamente.");
                }else{
                    respuesta.setMensaje("Lo sentimos, la unidad no pudo ser actualizada.");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexión a la BD.");
        }
        return respuesta;
    }
    
    public static List<Unidad> buscarUnidad(String busqueda){
        List<Unidad> unidades= null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {              
                unidades= conexionBD.selectList("unidad.buscar-unidad", busqueda);
                if(unidades!=null){
                    return unidades;
                }
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }else{
            throw new RuntimeException();
        }
        return unidades;
    }
    
    //metodo
    /*public static String generarNII(int año, String VIN){
        String NII;
        NII= año+VIN.substring(0, 4);
        return NII;
    }*/
}
