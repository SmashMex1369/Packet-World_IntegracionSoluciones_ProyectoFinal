package packetworldclienteescritorio.pojo;


/**
 *
 * @author alex4
 */

public class Conductor extends Colaborador{
    private Integer idConductor;
    private String noLicencia;

    public Conductor() {
    }    

    public Conductor(Integer idConductor, String noLicencia, Integer idColaborador, String noPersonal, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contraseña, String CURP, Integer estatus, String fotoBase64, byte[] fotografia, Integer idRol, String rol, Integer idSucursal, String CUS) {
        super(idColaborador, noPersonal, nombre, apellidoPaterno, apellidoMaterno, correo, contraseña, CURP, estatus, fotoBase64, fotografia, idRol, rol, idSucursal, CUS);
        this.idConductor = idConductor;
        this.noLicencia = noLicencia;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public String getNoLicencia() {
        return noLicencia;
    }

    public void setNoLicencia(String noLicencia) {
        this.noLicencia = noLicencia;
    }
    
    
}
