package ws;

import dominio.AutenticacionImp;
import dto.RSAutenticacionColaborador;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("autenticacion")
public class AutenticacionWS {
    @Path("colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RSAutenticacionColaborador autenticarColaborador(
            @FormParam("noPersonal") String noPersonal,
            @FormParam("contraseña") String contraseña){
        if ((noPersonal != null && !noPersonal.isEmpty()) && (contraseña != null && !contraseña.isEmpty())){
            return AutenticacionImp.autenticarColaborador(noPersonal, contraseña);       
        }
        throw  new BadRequestException();
    }

}
