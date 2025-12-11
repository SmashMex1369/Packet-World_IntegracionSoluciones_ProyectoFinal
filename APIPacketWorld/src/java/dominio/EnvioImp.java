package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import utilidades.Constantes;

/**
 * @author alex4
 */

public class EnvioImp {
    public static List<Envio> obtenerEnvios(){
        List<Envio> envios = null;  
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                envios = conexionBD.selectList("envio.obtener-envios");
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return envios;
    }
    
    public static Respuesta crearEnvio(Envio envio){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                int filasAfectadas = conexionBD.insert("envio.crear-envio", envio);
                if (filasAfectadas == 1) {
                    Envio historialEnvio = new Envio();
                    historialEnvio.setIdEnvio(envio.getIdEnvio());
                    historialEnvio.setIdColaborador(envio.getIdColaborador());
                    filasAfectadas= conexionBD.insert("envio.crear-estatus", historialEnvio);
                    if(filasAfectadas ==1){
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("Se ha creado el envio con número de guía: "+envio.getNoGuia());
                    }else{
                        conexionBD.rollback();
                        respuesta.setMensaje("Lo sentimos, fallo el registro del historial, por lo tanto, no se creo el envio.");
                    }
                }else{
                    respuesta.setMensaje("Lo sentimos, el envio no pudo ser creado.");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta actualizarEnvio(Envio envio){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!= null ) {
            try {
                int filasAfectadas= conexionBD.update("envio.actualizar-envio", envio);
                conexionBD.commit();
                if(filasAfectadas==1){
                    respuesta.setError(false);
                    respuesta.setMensaje("Se ha actualizado el envio.");
                }else{
                    respuesta.setMensaje("Lo sentimos, el envio no pudo ser actualizado.");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta actualizarEstatus(Envio envio){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD!= null){
            try {
                int filasAfectadas = conexionBD.insert("envio.actualizar-estatus", envio);
                conexionBD.commit();
                if(filasAfectadas == 1){
                    respuesta.setError(false);
                    respuesta.setMensaje("Se ha actualizado el estatus del envio.");
                }else{
                    respuesta.setMensaje("Lo sentimos, el estatus del envio no pudo ser actualizado.");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static List<Envio> buscarEnvio(String noGuia){
        List<Envio> envios = null;  
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                envios = conexionBD.selectList("envio.buscar-envio", noGuia);
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return envios;
    }
    
}
