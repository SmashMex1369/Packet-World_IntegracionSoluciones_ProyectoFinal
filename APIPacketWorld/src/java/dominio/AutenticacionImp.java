package dominio;

import dto.RSAutenticacionColaborador;
import java.util.HashMap;
import java.util.LinkedHashMap;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;

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
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexión a la base de datos");
        }
        return respuesta;
    }
}
