package ws;

import com.google.gson.Gson;
import dominio.ClienteImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Cliente;

/**
 *
 * @author OmarVX
 */

@Path("cliente")
public class ClientesWS {
    
@Path("obtener-clientes")
@GET
@Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerProfesores(){
    return ClienteImp.obtenerClientes();
    }
        
@Path("registrar-cliente")
@POST
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Respuesta registrar(String json){
    Gson gson = new Gson();
    try{
        Cliente cliente = gson.fromJson(json, Cliente.class);
        return ClienteImp.registrarCliente(cliente);
    }catch(Exception e){
        throw new BadRequestException();
    }
}

@Path("editar-cliente")
@PUT
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Respuesta editar(String json){
    Gson gson = new Gson();
        try{
            Cliente cliente = gson.fromJson(json, Cliente.class);
            return ClienteImp.editarCliente(cliente);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

@Path("eliminar-cliente/{idCliente}")
@DELETE
@Produces(MediaType.APPLICATION_JSON)
public Respuesta eliminar(@PathParam("idCliente") Integer idCliente){
        return ClienteImp.eliminarCliente(idCliente);
    }
    
}
