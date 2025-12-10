package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.NoGuia;
import pojo.Rol;


/**
 *
 * @author alex4
 */

public class CatalogoImp {
    public static List<NoGuia> obtenerNoGuiaDisponibles(){
        List<NoGuia> noGuias = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                noGuias=conexionBD.selectList("catalogo.obtener-noguia-disponibles");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return noGuias;
    }
    
    public static List<Rol> obtenerRoles(){
        List<Rol> rol = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                rol = conexionBD.selectList("catalogo.obtener-roles");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rol;
    } 
}
