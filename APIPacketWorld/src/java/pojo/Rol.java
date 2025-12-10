package pojo;


/**
 *
 * @author alex4
 */

public class Rol {
    private Integer idRol;
    private String rol;

    public Rol() {
    }
    
    public Rol(Integer idRol, String rol) {
        this.idRol = idRol;
        this.rol = rol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
