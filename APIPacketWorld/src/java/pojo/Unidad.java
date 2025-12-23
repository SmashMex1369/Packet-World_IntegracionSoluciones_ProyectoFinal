package pojo;

/**
 *
 * @author citla
 */
public class Unidad {
    
    private Integer idUnidad;
    private String VIN;
    private String NII;
    private Integer año;
    private String modelo;
    private String marca;
    private Integer idTipoUnidad;
    private int idConductor;
    private String noLicencia;
    private String tipo;
    private Integer idEstatusUnidad;
    private Integer estatus;
    private String motivo;
    private String tiempo;
    private Integer idColaborador;
    private String nombreColaborador;
    private String apellidoPatColaborador;
    private String apellidoMatColaborador;
    private String nombreConductor;
    private String apellidoPatConductor;
    private String apellidoMatConductor;    

    public Unidad() {
    }

    public Unidad(Integer idUnidad, String VIN, String NII, Integer año, String modelo, String marca, Integer idTipoUnidad, int idConductor, String noLicencia, String tipo, Integer idEstatusUnidad, Integer estatus, String motivo, String tiempo, Integer idColaborador, String nombreColaborador, String apellidoPatColaborador, String apellidoMatColaborador, String nombreConductor, String apellidoPatConductor, String apellidoMatConductor, String noPersonal) {
        this.idUnidad = idUnidad;
        this.VIN = VIN;
        this.NII = NII;
        this.año = año;
        this.modelo = modelo;
        this.marca = marca;
        this.idTipoUnidad = idTipoUnidad;
        this.idConductor = idConductor;
        this.noLicencia = noLicencia;
        this.tipo = tipo;
        this.idEstatusUnidad = idEstatusUnidad;
        this.estatus = estatus;
        this.motivo = motivo;
        this.tiempo = tiempo;
        this.idColaborador = idColaborador;
        this.nombreColaborador = nombreColaborador;
        this.apellidoPatColaborador = apellidoPatColaborador;
        this.apellidoMatColaborador = apellidoMatColaborador;
        this.nombreConductor = nombreConductor;
        this.apellidoPatConductor = apellidoPatConductor;
        this.apellidoMatConductor = apellidoMatConductor;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getNII() {
        return NII;
    }

    public void setNII(String NII) {
        this.NII = NII;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNoLicencia() {
        return noLicencia;
    }

    public void setNoLicencia(String noLicencia) {
        this.noLicencia = noLicencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdEstatusUnidad() {
        return idEstatusUnidad;
    }

    public void setIdEstatusUnidad(Integer idEstatusUnidad) {
        this.idEstatusUnidad = idEstatusUnidad;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
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


}
