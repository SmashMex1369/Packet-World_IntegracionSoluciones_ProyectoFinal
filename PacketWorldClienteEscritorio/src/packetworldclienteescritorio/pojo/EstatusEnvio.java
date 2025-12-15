package packetworldclienteescritorio.pojo;


/**
 *
 * @author alex4
 */

public class EstatusEnvio {
    private Integer idEstatusEnvio;
    private String estatus;

    public EstatusEnvio() {
    }

    public EstatusEnvio(Integer idEstatusEnvio, String estatus) {
        this.idEstatusEnvio = idEstatusEnvio;
        this.estatus = estatus;
    }

    public Integer getIdEstatusEnvio() {
        return idEstatusEnvio;
    }

    public void setIdEstatusEnvio(Integer idEstatusEnvio) {
        this.idEstatusEnvio = idEstatusEnvio;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return estatus;
    }
    
}
