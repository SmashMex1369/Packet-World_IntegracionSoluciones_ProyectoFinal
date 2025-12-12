package packetworldclienteescritorio.pojo;

/**
 * @author alex4
 */

public class Envio {
    private Integer idEnvio;
    private String noGuia;
    private String nombreDest;
    private String apellidoPatDest;
    private String apellidoMatDest;
    private Integer numDest;
    private String calleDest;
    
    private Integer idCliente;
    private String nombreCliente;
    private String apellidoPatCliente;
    private String apellidoMatCliente;
    private String correoCliente;
    private String telefonoCliente;
    private Integer numeroCliente;
    private String calleCliente;
    private Integer idColoniaCliente;
    private Integer codigoPostalCliente;
    private String coloniaCliente;
    
    private Integer idSucursal;
    private String nombreSucursal;
    private String CUSSucursal;
    private String numeroSucursal;
    private String calleSucursal;
    private Integer idColoniaSucursal;
    private Integer codigoPostalSucursal;
    private String coloniaSucursal;
    private String ciudadSucursal;
    private String estadoSucursal;
    
    private int idConductor;
    private String noLicenciaConductor;
    private String nombreConductor;
    private String apellidoPatConductor;
    private String apellidoMatConductor;
    
    private Integer idColoniaDest;
    private Integer codigoPostalDest;
    private String coloniaDest;
    private String ciudadDest;
    private String estadoDest;
    
    private Integer idHistorialEstatusEnvio;
    private Integer idEstatusEnvio;
    private String estatus;
    private String motivo;
    private String tiempo;
    
    private Integer idColaborador;
    private String nombreColaborador;
    private String apellidoPatColaborador;
    private String apellidoMatColaborador;

    public Envio() {
    }

    public Envio(Integer idEnvio, String noGuia, String nombreDest, String apellidoPatDest, String apellidoMatDest, Integer numDest, String calleDest, Integer idCliente, String nombreCliente, String apellidoPatCliente, String apellidoMatCliente, String correoCliente, String telefonoCliente, Integer numeroCliente, String calleCliente, Integer idColoniaCliente, Integer codigoPostalCliente, String coloniaCliente, Integer idSucursal, String nombreSucursal, String CUSSucursal, String numeroSucursal, String calleSucursal, Integer idColoniaSucursal, Integer codigoPostalSucursal, String coloniaSucursal, String ciudadSucursal, String estadoSucursal, int idConductor, String noLicenciaConductor, String nombreConductor, String apellidoPatConductor, String apellidoMatConductor, Integer idColoniaDest, Integer codigoPostalDest, String coloniaDest, String ciudadDest, String estadoDest, Integer idHistorialEstatusEnvio, Integer idEstatusEnvio, String estatus, String motivo, String tiempo, Integer idColaborador, String nombreColaborador, String apellidoPatColaborador, String apellidoMatColaborador) {
        this.idEnvio = idEnvio;
        this.noGuia = noGuia;
        this.nombreDest = nombreDest;
        this.apellidoPatDest = apellidoPatDest;
        this.apellidoMatDest = apellidoMatDest;
        this.numDest = numDest;
        this.calleDest = calleDest;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoPatCliente = apellidoPatCliente;
        this.apellidoMatCliente = apellidoMatCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.numeroCliente = numeroCliente;
        this.calleCliente = calleCliente;
        this.idColoniaCliente = idColoniaCliente;
        this.codigoPostalCliente = codigoPostalCliente;
        this.coloniaCliente = coloniaCliente;
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.CUSSucursal = CUSSucursal;
        this.numeroSucursal = numeroSucursal;
        this.calleSucursal = calleSucursal;
        this.idColoniaSucursal = idColoniaSucursal;
        this.codigoPostalSucursal = codigoPostalSucursal;
        this.coloniaSucursal = coloniaSucursal;
        this.ciudadSucursal = ciudadSucursal;
        this.estadoSucursal = estadoSucursal;
        this.idConductor = idConductor;
        this.noLicenciaConductor = noLicenciaConductor;
        this.nombreConductor = nombreConductor;
        this.apellidoPatConductor = apellidoPatConductor;
        this.apellidoMatConductor = apellidoMatConductor;
        this.idColoniaDest = idColoniaDest;
        this.codigoPostalDest = codigoPostalDest;
        this.coloniaDest = coloniaDest;
        this.ciudadDest = ciudadDest;
        this.estadoDest = estadoDest;
        this.idHistorialEstatusEnvio = idHistorialEstatusEnvio;
        this.idEstatusEnvio = idEstatusEnvio;
        this.estatus = estatus;
        this.motivo = motivo;
        this.tiempo = tiempo;
        this.idColaborador = idColaborador;
        this.nombreColaborador = nombreColaborador;
        this.apellidoPatColaborador = apellidoPatColaborador;
        this.apellidoMatColaborador = apellidoMatColaborador;
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

    public String getNombreDest() {
        return nombreDest;
    }

    public void setNombreDest(String nombreDest) {
        this.nombreDest = nombreDest;
    }

    public String getApellidoPatDest() {
        return apellidoPatDest;
    }

    public void setApellidoPatDest(String apellidoPatDest) {
        this.apellidoPatDest = apellidoPatDest;
    }

    public String getApellidoMatDest() {
        return apellidoMatDest;
    }

    public void setApellidoMatDest(String apellidoMatDest) {
        this.apellidoMatDest = apellidoMatDest;
    }

    public Integer getNumDest() {
        return numDest;
    }

    public void setNumDest(Integer numDest) {
        this.numDest = numDest;
    }

    public String getCalleDest() {
        return calleDest;
    }

    public void setCalleDest(String calleDest) {
        this.calleDest = calleDest;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoPatCliente() {
        return apellidoPatCliente;
    }

    public void setApellidoPatCliente(String apellidoPatCliente) {
        this.apellidoPatCliente = apellidoPatCliente;
    }

    public String getApellidoMatCliente() {
        return apellidoMatCliente;
    }

    public void setApellidoMatCliente(String apellidoMatCliente) {
        this.apellidoMatCliente = apellidoMatCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getCalleCliente() {
        return calleCliente;
    }

    public void setCalleCliente(String calleCliente) {
        this.calleCliente = calleCliente;
    }

    public Integer getIdColoniaCliente() {
        return idColoniaCliente;
    }

    public void setIdColoniaCliente(Integer idColoniaCliente) {
        this.idColoniaCliente = idColoniaCliente;
    }

    public Integer getCodigoPostalCliente() {
        return codigoPostalCliente;
    }

    public void setCodigoPostalCliente(Integer codigoPostalCliente) {
        this.codigoPostalCliente = codigoPostalCliente;
    }

    public String getColoniaCliente() {
        return coloniaCliente;
    }

    public void setColoniaCliente(String coloniaCliente) {
        this.coloniaCliente = coloniaCliente;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getCUSSucursal() {
        return CUSSucursal;
    }

    public void setCUSSucursal(String CUSSucursal) {
        this.CUSSucursal = CUSSucursal;
    }

    public String getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(String numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public String getCalleSucursal() {
        return calleSucursal;
    }

    public void setCalleSucursal(String calleSucursal) {
        this.calleSucursal = calleSucursal;
    }

    public Integer getIdColoniaSucursal() {
        return idColoniaSucursal;
    }

    public void setIdColoniaSucursal(Integer idColoniaSucursal) {
        this.idColoniaSucursal = idColoniaSucursal;
    }

    public Integer getCodigoPostalSucursal() {
        return codigoPostalSucursal;
    }

    public void setCodigoPostalSucursal(Integer codigoPostalSucursal) {
        this.codigoPostalSucursal = codigoPostalSucursal;
    }

    public String getColoniaSucursal() {
        return coloniaSucursal;
    }

    public void setColoniaSucursal(String coloniaSucursal) {
        this.coloniaSucursal = coloniaSucursal;
    }

    public String getCiudadSucursal() {
        return ciudadSucursal;
    }

    public void setCiudadSucursal(String ciudadSucursal) {
        this.ciudadSucursal = ciudadSucursal;
    }

    public String getEstadoSucursal() {
        return estadoSucursal;
    }

    public void setEstadoSucursal(String estadoSucursal) {
        this.estadoSucursal = estadoSucursal;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNoLicenciaConductor() {
        return noLicenciaConductor;
    }

    public void setNoLicenciaConductor(String noLicenciaConductor) {
        this.noLicenciaConductor = noLicenciaConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getApellidoPatConductor() {
        return apellidoPatConductor;
    }

    public void setApellidoPatConductor(String apellidoPatConductor) {
        this.apellidoPatConductor = apellidoPatConductor;
    }

    public String getApellidoMatConductor() {
        return apellidoMatConductor;
    }

    public void setApellidoMatConductor(String apellidoMatConductor) {
        this.apellidoMatConductor = apellidoMatConductor;
    }

    public Integer getIdColoniaDest() {
        return idColoniaDest;
    }

    public void setIdColoniaDest(Integer idColoniaDest) {
        this.idColoniaDest = idColoniaDest;
    }

    public Integer getCodigoPostalDest() {
        return codigoPostalDest;
    }

    public void setCodigoPostalDest(Integer codigoPostalDest) {
        this.codigoPostalDest = codigoPostalDest;
    }

    public String getColoniaDest() {
        return coloniaDest;
    }

    public void setColoniaDest(String coloniaDest) {
        this.coloniaDest = coloniaDest;
    }

    public String getCiudadDest() {
        return ciudadDest;
    }

    public void setCiudadDest(String ciudadDest) {
        this.ciudadDest = ciudadDest;
    }

    public String getEstadoDest() {
        return estadoDest;
    }

    public void setEstadoDest(String estadoDest) {
        this.estadoDest = estadoDest;
    }

    public Integer getIdHistorialEstatusEnvio() {
        return idHistorialEstatusEnvio;
    }

    public void setIdHistorialEstatusEnvio(Integer idHistorialEstatusEnvio) {
        this.idHistorialEstatusEnvio = idHistorialEstatusEnvio;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    public String getApellidoPatColaborador() {
        return apellidoPatColaborador;
    }

    public void setApellidoPatColaborador(String apellidoPatColaborador) {
        this.apellidoPatColaborador = apellidoPatColaborador;
    }

    public String getApellidoMatColaborador() {
        return apellidoMatColaborador;
    }

    public void setApellidoMatColaborador(String apellidoMatColaborador) {
        this.apellidoMatColaborador = apellidoMatColaborador;
    }
    
}
