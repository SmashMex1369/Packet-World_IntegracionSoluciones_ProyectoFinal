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
 * @author OmarVX
 */
public class FXMLAdministracionClientesController implements Initializable {

    @FXML
    private TextField tfBusqueda;
    @FXML
    private TableView<?> tvClientes;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidoPaterno;
    @FXML
    private TableColumn<?, ?> colApellidoMaterno;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicEliminar(ActionEvent event) {
    }

    @FXML
    private void clicIrEditar(ActionEvent event) {
    }

    @FXML
    private void clicIrRegistrar(ActionEvent event) {
    }
    
}
