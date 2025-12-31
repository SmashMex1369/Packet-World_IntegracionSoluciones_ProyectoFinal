package dominio;

import dto.RSBuscarEnvioWeb;
import dto.Respuesta;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.Paquete;
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
                if (envios != null) {
                    List<Paquete> paquetes = null;
                    for(int i = 0; i < envios.size(); i++){
                        paquetes = conexionBD.selectList("envio.obtener-paquetes", envios.get(i).getIdEnvio());
                        if (paquetes != null) {
                            envios.get(i).setPaquetes(paquetes);
                        }
                    }
                }
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
                if (envios != null) {
                    List<Paquete> paquetes = null;
                    for(int i = 0; i < envios.size(); i++){
                        paquetes = conexionBD.selectList("envio.obtener-paquetes", envios.get(i).getIdEnvio());
                        if (paquetes != null) {
                            envios.get(i).setPaquetes(paquetes);
                        }
                    }
                }
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return envios;
    }
    
    public static RSBuscarEnvioWeb buscarEnvioWeb(String noGuia){
        RSBuscarEnvioWeb respuesta = new RSBuscarEnvioWeb();  
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Envio envio = conexionBD.selectOne("envio.buscar-envio-web", noGuia);
                if (envio != null) {
                    respuesta.setError(false);
                    respuesta.setMensaje("El envio fue encontrado");
                    respuesta.setEnvio(envio);
                }else{
                    respuesta.setMensaje("El número de guia ingresado no existe, favor de verificarlo");
                }
                conexionBD.close();
            }catch(Exception e){
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static List<Envio> historialEstatusEnvio(String noGuia){
        List<Envio> envios = null;  
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                envios = conexionBD.selectList("envio.historial-estatus-envio", noGuia);
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return envios;
    }
    
    public static Respuesta verificarNoGuia(String noGuia){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                if(conexionBD.selectOne("envio.verificar-no-guia", noGuia)==null){            
                    respuesta.setError(false);  
                    respuesta.setMensaje("Numero de guia no existe");
                }else{
                    respuesta.setMensaje("Numero de guia existe");
                }
                conexionBD.close() ;         
            } catch (Exception e) {
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta asignarConductor(Integer idConductor, Integer idEnvio){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                HashMap<String, Integer> parametros = new LinkedHashMap<>();
                parametros.put("idConductor", idConductor);
                parametros.put("idEnvio", idEnvio);
                int filasAfectadas = conexionBD.update("envio.asignar-conductor", parametros);
                if (filasAfectadas == 1) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("El conductor seleccionado ha sido asignado");
                }else{
                    conexionBD.rollback();
                    respuesta.setMensaje("Lo sentimos, el conductor no pudo ser asignado");
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
    
    public static List<Envio> obtenerEnviosConductor(Integer idConductor){
        List<Envio> envios = null;  
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                envios = conexionBD.selectList("envio.obtener-envios-conductor", idConductor);
                if (envios != null) {
                    List<Paquete> paquetes = null;
                    for(int i = 0; i < envios.size(); i++){
                        paquetes = conexionBD.selectList("envio.obtener-paquetes", envios.get(i).getIdEnvio());
                        if (paquetes != null) {
                            envios.get(i).setPaquetes(paquetes);
                        }
                    }
                }
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return envios;
    }
    
}
