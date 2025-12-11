package pojo;


/**
 *
 * @author alex4
 */

public class Direccion {
    private Integer idColonia;
    private Integer codigoPostal;
    private String colonia;
    private String ciudad;
    private String estado;

    public Direccion() {
    }

    public Direccion(Integer idColonia, Integer codigoPostal, String colonia, String ciudad, String estado) {
        this.idColonia = idColonia;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
