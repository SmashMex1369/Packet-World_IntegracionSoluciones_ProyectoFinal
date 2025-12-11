package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Paquete;

/**
 *
 * @author citla
 */
public class PaqueteImp {
    
    public static List<Paquete> obtenerPaquetes(){
        List <Paquete> paquetes= null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                paquetes= conexionBD.selectList("paquete.obtener-paquetes");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paquetes;
    }
    
    public static Respuesta registrarPaquete(Paquete paquete){
        Respuesta respuesta= new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if (conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.insert("paquete.registrar", paquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("El paquete ha sido registrado correctamente.");
                }else{
                    respuesta.setMensaje("Lo sentimos, el paquete no pudo ser registrado.");
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
    
    public static Respuesta editarPaquete(Paquete paquete){
        Respuesta respuesta= new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("paquete.editar", paquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("El paquete ha sido actualizado correctamente.");
                }else{
                    respuesta.setMensaje("Lo sentimos, el paquete no pudo ser actualizado.");
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
    
    public static Respuesta eliminarPaquete(int idPaquete){
        Respuesta respuesta= new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.delete("paquete.eliminar", idPaquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    respuesta.setError(false);
                    respuesta.setMensaje("El paquete ha sido eliminado correctamente.");
                }else{
                    respuesta.setMensaje("Lo sentimos, el paquete no pudo ser eliminado.");
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
    
    public static List<Paquete> obtenerPaquetePorNoGuia(String noGuia){
        List<Paquete> paquetes=null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                 paquetes= conexionBD.selectList("paquete.buscar-paquete", noGuia);
                 conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }          
        }
        return paquetes;
    }
}
