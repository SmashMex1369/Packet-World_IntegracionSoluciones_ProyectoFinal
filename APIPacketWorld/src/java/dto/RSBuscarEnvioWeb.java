package dto;

import pojo.Envio;


/**
 *
 * @author alex4
 */

public class RSBuscarEnvioWeb {
    private boolean error;
    private String mensaje;
    private Envio envio;

    public RSBuscarEnvioWeb() {
    }

    public RSBuscarEnvioWeb(boolean error, String mensaje, Envio envio) {
        this.error = error;
        this.mensaje = mensaje;
        this.envio = envio;
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

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
    
}
