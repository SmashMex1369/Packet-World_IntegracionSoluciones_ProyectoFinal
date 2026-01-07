package dominio;

import dto.Respuesta;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Conductor;
import pojo.Unidad;
import utilidades.Constantes;

/**
 *
 * @author citla
 */
public class UnidadImp {
    
    public static List<Unidad> obtenerUnidadesDisponibles(){
        List<Unidad> unidades=null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                unidades= conexionBD.selectList("unidad.obtener-unidades-disponibles");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static List<Unidad> obtenerUnidadesInactivas(){
        List<Unidad> unidades=null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                unidades= conexionBD.selectList("unidad.obtener-unidades-inactivas");
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
                if (filasAfectadas>0){
                    Unidad historialUnidad= new Unidad();
                    historialUnidad.setIdUnidad(unidad.getIdUnidad());
                    historialUnidad.setIdColaborador(unidad.getIdColaborador());
                    filasAfectadas= conexionBD.insert("unidad.registrar-estatus", historialUnidad);
                    if(filasAfectadas>0){
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("La unidad ha sido registrada correctamente.");
                    }else{
                       conexionBD.rollback();
                       respuesta.setMensaje("Lo sentimos, fallo el registro del historial, por lo tanto, no se creo la unidad.");
                    }             
                }else{
                    respuesta.setMensaje("Lo sentimos, la unidad no pudo ser registrada.");
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
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }           
        }
        return unidades;
    }
    
    public static Respuesta darBajaUnidad(Unidad unidad){
        Respuesta respuesta= new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if (conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.insert("unidad.dar-baja", unidad);
                if(filasAfectadas>0){
                conexionBD.commit();
                respuesta.setError(false);
                respuesta.setMensaje("La unidad ha sido de baja correctamente.");
                }else{
                    respuesta.setMensaje("Lo sentimos, la unidad no pudo ser dada de baja.");
                }
                conexionBD.close();
            }catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static List<Unidad> buscarHistorialUnidad(String busqueda){
        List<Unidad> unidades= null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {              
                unidades= conexionBD.selectList("unidad.buscar-historial-unidad", busqueda);               
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }           
        }
        return unidades;
    }
    
    public static Respuesta asignarUnidadAConductor(Integer idConductor, Integer idUnidad){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                HashMap<String, Integer> parametros = new LinkedHashMap<>();
                parametros.put("idConductor", idConductor);
                parametros.put("idUnidad", idUnidad);
                int filasAfectadas= conexionBD.update("unidad.asignar-unidad-conductor", parametros);
                if(filasAfectadas>0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("La unidad ha sido asociada al conductor seleccionado.");
                }else{
                    conexionBD.rollback();
                    respuesta.setMensaje("Lo sentimos, la unidad no pudo ser asignada.");
                }
                conexionBD.close();
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);    
        }
       return respuesta; 
    }
    
    public static List<Conductor> conductoresSinUnidad(){
        List<Conductor> conductores=null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                conductores= conexionBD.selectList("unidad.conductores-sin-unidad");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conductores;
    }
    
    public static Respuesta desasignarConductor(int idUnidad){
        Respuesta respuesta= new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("unidad.desasignar-conductor", idUnidad);
                if(filasAfectadas>0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("El conductor ha sido desasignado.");
                }else{
                    respuesta.setMensaje("Lo sentimos, el conductor no pudo ser desasignado.");
                }
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static List<Conductor> buscarConductores(String busqueda){
        List<Conductor> conductores=null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if (conexionBD!=null){
            try {
                conductores= conexionBD.selectList("unidad.buscar-asociacion", busqueda);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conductores;
    }
    
    public static Respuesta verificarVIN(String VIN){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                if(conexionBD.selectOne("unidad.validar-vin", VIN)==null){
                    respuesta.setError(false);
                    respuesta.setMensaje("El VIN esta disponible");
                }else{
                    respuesta.setMensaje("El VIN ingresado ya esta en registrado");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje((Constantes.MSJ_ERROR_BD));
        }
        return respuesta;
    }
    
}
