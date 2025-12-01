package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLFormularioUnidadesController implements Initializable {

    @FXML
    private ComboBox<?> cbTipoUnidad;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    
}
