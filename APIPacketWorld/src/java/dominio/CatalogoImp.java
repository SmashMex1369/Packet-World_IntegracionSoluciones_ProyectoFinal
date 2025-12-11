package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.CUS;
import pojo.Direccion;
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
    
    public static List<CUS> obtenerSucursalesDisponibles(){
        List<CUS> sucursales = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null) {
            try {
                sucursales = conexionBD.selectList("catalogo.obtener-sucursal-disponibles");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sucursales;
    }
    
    public static List<Direccion> obtenerDireccion(String codigoPostal){
        List<Direccion> direccion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD!=null){
            try {
                direccion = conexionBD.selectList("catalogo.obtener-ciudad-estado", codigoPostal);
                direccion.addAll(conexionBD.selectList("catalogo.obtener-colonias", codigoPostal));
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return direccion;
    }
    
    
}
