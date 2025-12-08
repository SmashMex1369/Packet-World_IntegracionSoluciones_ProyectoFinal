package pojo;


/**
 *
 * @author alex4
 */

public class NoGuia {
    private Integer idEnvio;
    private String noGuia;

    public NoGuia() {
    }
    
    public NoGuia(Integer idEnvio, String noGuia) {
        this.idEnvio = idEnvio;
        this.noGuia = noGuia;
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
    
    
}
