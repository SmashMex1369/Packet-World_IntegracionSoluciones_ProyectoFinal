package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import utilidades.Constantes;

/**
 *
 * @author OmarVX
 */
public class ClienteImp {
    
    public static List<Cliente> obtenerClientes(){
        List<Cliente> clientes = null;  
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                clientes = conexionBD.selectList("cliente.obtener-clientes");
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return clientes;
    }
    
    public static Respuesta registrarCliente(Cliente cliente){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("cliente.registrar-cliente", cliente);
                conexionBD.commit();
                    if(filasAfectadas > 0){
                        respuesta.setError(false);
                        respuesta.setMensaje("Cliente " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + " agregado con éxito");
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
    
    public static Respuesta editarCliente(Cliente cliente){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("cliente.editar-cliente", cliente);
                conexionBD.commit();
                    if(filasAfectadas > 0){
                        respuesta.setError(false);
                        respuesta.setMensaje("Cliente " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + " actualizado con éxito");
                    }else{
                        respuesta.setError(true);
                        respuesta.setMensaje("No se pudo actualizar, intente más tarde");
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
    
    public static Respuesta eliminarCliente(int  idCliente){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.delete("cliente.eliminar-cliente", idCliente);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("La información del cliente fue eliminada con éxito");
                }else{
                    respuesta.setMensaje("No se pudo eliminar, intente más tarde");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
            }
        }
        return respuesta;
    }
    
}
