package dominio;

import dto.Respuesta;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Conductor;
import utilidades.Constantes;


/**
 *
 * @author alex4
 */

public class ColaboradorImp {
    
    public static List<Conductor> obtenerColaboradores(){
        List<Conductor> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaborador.obtener-colaboradores");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static List<Conductor> buscarColaborador(String busqueda){
        List<Conductor> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaborador.buscar-colaborador",busqueda);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static List<Conductor> obtenerColaboradoresRol(Integer idRol){
        List<Conductor> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                colaboradores = conexionBD.selectList("colaborador.obtener-colaboradores-rol", idRol);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static List<Conductor> buscarColaboradorRol(Integer idRol, String busqueda){
        List<Conductor> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                HashMap<String, Object> parametros = new LinkedHashMap<>();
                parametros.put("idRol", idRol);
                parametros.put("busqueda", busqueda);
                colaboradores = conexionBD.selectList("colaborador.buscar-colaborador-rol", parametros);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static Respuesta registrarColaborador(Conductor colaborador){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                int filasAfectadas = conexionBD.insert("colaborador.registrar-colaborador", colaborador);
                if(filasAfectadas==1){
                    if (colaborador.getIdRol()==3) {
                        filasAfectadas = conexionBD.insert("colaborador.registrar-conductor", colaborador);
                        if (filasAfectadas ==1) {
                            conexionBD.commit();
                            respuesta.setError(false);
                            respuesta.setMensaje("Se ha registrado el conductor con número de personal: "+colaborador.getNoPersonal());
                        }else{
                            conexionBD.rollback();
                            respuesta.setMensaje("Lo sentimos, el conductor no pudo ser registrado.");
                        }
                    } else {
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("Se ha registrado el colaborador con número de personal: "+colaborador.getNoPersonal());
                    }
                }else{
                    respuesta.setMensaje("Lo sentimos, el colaborador no pudo ser registrado.");
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
    
    public static Respuesta actualizarColaborador(Colaborador colaborador) {
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                int filasAfectadas;
                if (colaborador.getContraseña()==null) {
                    filasAfectadas = conexionBD.update("colaborador.actualizar-colaborador", colaborador);   
                }else{
                    filasAfectadas = conexionBD.update("colaborador.actualizar-colaborador-contraseña", colaborador);   
                }
                if(filasAfectadas==1){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("El colaborador ha sido actualizado corectamente.");
                }else{
                    respuesta.setMensaje("Lo sentimos, el colaborador no pudo ser actualizado.");
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
    
    public static Colaborador obtenerFoto(int idColaborador){
        Colaborador colaborador = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!= null) {
            try {
                colaborador = conexionBD.selectOne("colaborador.obtener-foto", idColaborador);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaborador;                
    }
    
    public static Respuesta subirFoto(int idColaborador, byte[] fotografia){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(idColaborador);
                colaborador.setFotografia(fotografia);
                int filasAfectadas = conexionBD.update("colaborador.subir-foto",colaborador);
                conexionBD.commit();
                if(filasAfectadas == 1){
                    respuesta.setError(false);
                    respuesta.setMensaje("La fotografia ha sido cambiada");
                }else{
                    respuesta.setMensaje("Lo sentimos, la fotografia no puedo ser cambiada");
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
    
    public static List<Conductor> obtenerConductoresSucursal(Integer idSucursal){
        List<Conductor> conductores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                conductores = conexionBD.selectList("colaborador.obtener-conductores-sucursal", idSucursal);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conductores;
    }
    
    public static List<Conductor> buscarConductorSucursal(Integer idSucursal, String busqueda){
        List<Conductor> conductores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                HashMap<String, Object> parametros = new LinkedHashMap<>();
                parametros.put("idSucursal", idSucursal);
                parametros.put("busqueda", busqueda);
                conductores = conexionBD.selectList("colaborador.buscar-conductores-sucursal", parametros);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conductores;
    }
    
}
