package dto;

import pojo.Conductor;


/**
 *
 * @author alex4
 */

public class RSAutenticacionConductor {
    private boolean error;
    private String mensaje;
    private Conductor conductor;

    public RSAutenticacionConductor() {
    }

    public RSAutenticacionConductor(boolean error, String mensaje, Conductor conductor) {
        this.error = error;
        this.mensaje = mensaje;
        this.conductor = conductor;
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

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }
    
}
