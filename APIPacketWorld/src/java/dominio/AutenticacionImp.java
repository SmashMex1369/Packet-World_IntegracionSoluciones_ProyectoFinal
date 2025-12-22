package dominio;

import dto.RSAutenticacionColaborador;
import dto.RSAutenticacionConductor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Conductor;
import utilidades.Constantes;

/**
 *
 * @author citla
 */
public class AutenticacionImp {
    
    public static RSAutenticacionColaborador autenticarColaborador(String noPersonal, String contraseña){
        
        RSAutenticacionColaborador respuesta= new RSAutenticacionColaborador();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                HashMap<String, String> parametros= new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contraseña", contraseña);
                Colaborador colaborador= conexionBD.selectOne("autenticacion.colaborador", parametros);
                if(colaborador!=null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del colaborador"+colaborador.getNombre());
                    respuesta.setColaborador(colaborador);
                }else{
                    respuesta.setMensaje("Credenciales incorrectas");
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
    
    public static RSAutenticacionConductor autenticarConductor(String noPersonal, String contraseña){
        
        RSAutenticacionConductor respuesta= new RSAutenticacionConductor();
        respuesta.setError(true);
        SqlSession conexionBD= MyBatisUtil.getSession();
        if(conexionBD!=null){
            try {
                HashMap<String, String> parametros= new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contraseña", contraseña);
                Conductor conductor = conexionBD.selectOne("autenticacion.conductor", parametros);
                if(conductor!=null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del conductor "+conductor.getNombre());
                    respuesta.setConductor(conductor);
                }else{
                    respuesta.setMensaje("Credenciales incorrectas");
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
    
}
