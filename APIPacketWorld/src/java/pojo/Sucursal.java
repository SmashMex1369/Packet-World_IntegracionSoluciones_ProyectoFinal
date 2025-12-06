package pojo;

/**
 *
 * @author OmarVX
 */
public class Sucursal {
    
    private int idSucursal;
    private String CUS;
    private String nombre;
    private int estatus;
    private String calle;
    private int numero;
    private int idColonia;
    private String colonia;
    private int codigoPostal;
    private String ciudad;
    private String estado;

    public Sucursal() {
    }

    public Sucursal(int idSucursal, String CUS, String nombre, int estatus, String calle, int numero, int idColonia, String colonia, int codigoPostal, String ciudad, String estado) {
        this.idSucursal = idSucursal;
        this.CUS = CUS;
        this.nombre = nombre;
        this.estatus = estatus;
        this.calle = calle;
        this.numero = numero;
        this.idColonia = idColonia;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getCUS() {
        return CUS;
    }

    public void setCUS(String CUS) {
        this.CUS = CUS;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
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
