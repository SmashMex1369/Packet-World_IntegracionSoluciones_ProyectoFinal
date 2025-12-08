package packetworldclienteescritorio.pojo;

/**
 *
 * @author citla
 */
public class Paquete {
    
    private Integer idPaquete;
    private String descripcion;
    private float peso;
    private float alto;
    private float ancho;
    private float profundidad;
    private Integer idEnvio;
    private String noGuia;

    public Paquete() {
    }

    public Paquete(Integer idPaquete, String descripcion, float peso, float alto, float ancho, float profundidad, Integer idEnvio, String noGuia) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.idEnvio = idEnvio;
        this.noGuia = noGuia;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(float profundidad) {
        this.profundidad = profundidad;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(String noGuia) {
        this.noGuia = noGuia;
    }

    @Override
    public String toString() {
        return noGuia;
    }
       
    
}
