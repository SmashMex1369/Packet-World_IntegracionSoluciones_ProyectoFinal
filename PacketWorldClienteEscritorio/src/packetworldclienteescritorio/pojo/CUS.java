package packetworldclienteescritorio.pojo;

/**
 *
 * @author OmarVX
 */
public class CUS {
   
    private Integer idSucursal;
    private String CUS;

    public CUS() {
    }

    public CUS(Integer idSucursal, String CUS) {
        this.idSucursal = idSucursal;
        this.CUS = CUS;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getCUS() {
        return CUS;
    }

    public void setCUS(String CUS) {
        this.CUS = CUS;
    }
    
    @Override
    public String toString() {
        return  CUS;
    }
    
}
