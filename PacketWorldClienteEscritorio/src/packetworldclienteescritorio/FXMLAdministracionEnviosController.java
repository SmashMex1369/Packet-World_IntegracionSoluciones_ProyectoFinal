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
 * @author alex4
 */
public class FXMLAdministracionEnviosController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<?> tvEnvios;
    @FXML
    private TableColumn<?, ?> colNoGuia;
    @FXML
    private TableColumn<?, ?> colNombreDestinatario;
    @FXML
    private TableColumn<?, ?> colApellidoPaternoDestinatario;
    @FXML
    private TableColumn<?, ?> colApellidoMaternoDestinatario;
    @FXML
    private TableColumn<?, ?> colEstado;
    @FXML
    private TableColumn<?, ?> colCodigoPostal;
    @FXML
    private TableColumn<?, ?> colConductor;

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
    private void btnCrear(ActionEvent event) {
    }

    @FXML
    private void btnAsignarConductor(ActionEvent event) {
    }

    @FXML
    private void btnConsultarDetalles(ActionEvent event) {
    }
    
}
