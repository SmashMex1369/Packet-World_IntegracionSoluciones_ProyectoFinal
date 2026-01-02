package packetworldclienteescritorio.dto;


/**
 *
 * @author alex4
 */

public class RespuestaCosto {
    private boolean error;
    private String mensaje;
    private Float distanciaKM;

    public RespuestaCosto() {
    }

    public RespuestaCosto(boolean error, String mensaje, Float distanciaKM) {
        this.error = error;
        this.mensaje = mensaje;
        this.distanciaKM = distanciaKM;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Float getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(Float distanciaKM) {
        this.distanciaKM = distanciaKM;
    }
    
    
}
