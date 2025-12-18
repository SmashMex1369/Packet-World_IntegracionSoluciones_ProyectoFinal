package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.pojo.Unidad;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLBajaUnidadController implements Initializable {

    @FXML
    private TextArea taMotivo;   
    @FXML
    private CheckBox ckbActivo;
    @FXML
    private Button btnBajas;
    
    private Unidad unidadEdicion;
    private INotificador observador;
    private Colaborador c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void cargarIdUnidad(Unidad unidadEdicion, INotificador observador){
        this.unidadEdicion= unidadEdicion;
        this.observador=observador;
        if(ckbActivo.isSelected()){
            taMotivo.setDisable(false);
            btnBajas.setDisable(false);
        }else{
            taMotivo.setDisable(true);
            btnBajas.setDisable(true);
        }
    }

    @FXML
    private void ckbActivo(ActionEvent event) {
        cargarIdUnidad(unidadEdicion, observador);
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        ((Stage)taMotivo.getScene().getWindow()).close();
    }

    @FXML
    private void btnBajas(ActionEvent event) {
    }
    
}
