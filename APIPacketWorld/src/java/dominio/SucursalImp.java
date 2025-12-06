package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Sucursal;
import utilidades.Constantes;

/**
 *
 * @author OmarVX
 */
public class SucursalImp {
    
    public static List<Sucursal> obtenerSucursales(){
        List<Sucursal> sucursales = null;  
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                sucursales = conexionBD.selectList("sucursal.obtener-sucursales");
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return sucursales;
    }
    
    public static Respuesta registrarSucursal(Sucursal sucursal){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("sucursal.registrar-sucursal", sucursal);
                conexionBD.commit();
                    if(filasAfectadas > 0){
                        respuesta.setError(false);
                        respuesta.setMensaje("Sucursal " + sucursal.getNombre() + " agregada con éxito");
                    }else{
                        respuesta.setError(true);
                        respuesta.setMensaje("No se pudo registrar, intente más tarde");
                    }
                    conexionBD.close();
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    
    public static Respuesta editarSucursal(Sucursal sucursal){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("sucursal.editar-sucursal", sucursal);
                
                    if(filasAfectadas > 0){
                        conexionBD.commit();
                        respuesta.setError(false);
                        respuesta.setMensaje("Sucursal " + sucursal.getNombre() + " actualizada con éxito");
                    }else{
                        conexionBD.rollback();
                        respuesta.setError(true);
                        respuesta.setMensaje("No se pudo actualizar la sucursal, intente más tarde");
                    }
                    
            }catch(Exception e){
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
}
