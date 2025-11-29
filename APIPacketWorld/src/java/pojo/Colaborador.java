package pojo;

/**
 *
 * @author citla
 */
public class Colaborador {
   
    private int idColaborador;
    private String noPersonal;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contraseña;
    private String CURP;
    private String fotoBase64;
    private byte[] fotografia;
    private int idRol;
    private String rol;
    private int idSucursal;
    private String CUS;

    public Colaborador() {
    }

    public Colaborador(int idColaborador, String noPersonal, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contraseña, String CURP, String fotoBase64, byte[] fotografia, int idRol, String rol, int idSucursal, String CUS) {
        this.idColaborador = idColaborador;
        this.noPersonal = noPersonal;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contraseña = contraseña;
        this.CURP = CURP;
        this.fotoBase64 = fotoBase64;
        this.fotografia = fotografia;
        this.idRol = idRol;
        this.rol = rol;
        this.idSucursal = idSucursal;
        this.CUS = CUS;
    }

    

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCus() {
        return CUS;
    }

    public void setCus(String CUS) {
        this.CUS = CUS;
    }
    
    
}
