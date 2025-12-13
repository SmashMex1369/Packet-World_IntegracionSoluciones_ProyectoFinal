package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.PaqueteImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.TipoUnidad;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLFormularioUnidadesController implements Initializable {

    @FXML
    private ComboBox<TipoUnidad> cbTipoUnidad;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfAnio;
    @FXML
    private TextField tfVIN;
    @FXML
    private TextField tfNII;
    
    private Unidad unidadEdicion;
    private INotificador observador;
    private ObservableList<TipoUnidad> tipos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposUnidad();
    }

    public void iniciarDatos(Unidad unidadEdicion, INotificador observador){
        this.unidadEdicion= unidadEdicion;
        this.observador= observador;
        if(unidadEdicion!=null){
            tfModel.setText(unidadEdicion.getModelo());
            tfMarca.setText(unidadEdicion.getMarca());
            tfAnio.setText(String.valueOf(unidadEdicion.getAño()));
            tfVIN.setText(unidadEdicion.getVIN());
            tfNII.setText(unidadEdicion.getNII());
            int posicionTipoUnidad= obtenerPosicionTipoUnidad(unidadEdicion.getIdTipoUnidad());
            cbTipoUnidad.getSelectionModel().select(posicionTipoUnidad);
        }
    }
    
    private int obtenerPosicionTipoUnidad(int idTipoUnidad){
        for (int i=0; i<tipos.size(); i++){
            if(tipos.get(i).getIdTipoUnidad()==idTipoUnidad){
                return i;
            }
        }
        return -1;
    }
    
    private void cargarTiposUnidad(){
        HashMap<String, Object> respuesta= CatalogoImp.obtenerTiposUnidad();
        if(!(boolean) respuesta.get(Constantes.KEY_ERROR)){
            List<TipoUnidad> tiposUnidadAPI= (List<TipoUnidad>)respuesta.get(Constantes.KEY_LISTA);
            tipos= FXCollections.observableArrayList();
            tipos.addAll(tiposUnidadAPI);
            cbTipoUnidad.setItems(tipos);
        }else{
            Utilidades.mostrarAlertaSimple(Constantes.KEY_ERROR, respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            //regresarVentana();
        }
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    
}
