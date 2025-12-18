package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLAsociacionUnidadConductorController implements Initializable {

    @FXML
    private TextField tfBuscarCond;
    @FXML
    private TableView<?> tvHistorialBajasUnidad;
    @FXML
    private TableColumn<?, ?> colVIN;
    @FXML
    private TableColumn<?, ?> colColaborador;
    @FXML
    private TableColumn<?, ?> colTiempo;
    @FXML
    private TableColumn<?, ?> colMotivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegresar(ActionEvent event) {
    }

    @FXML
    private void buscarConductor(ActionEvent event) {
    }

    @FXML
    private void btnAsignar(ActionEvent event) {
    }

    @FXML
    private void btnCambiar(ActionEvent event) {
    }

    @FXML
    private void btnDesasignar(ActionEvent event) {
    }
    
}
