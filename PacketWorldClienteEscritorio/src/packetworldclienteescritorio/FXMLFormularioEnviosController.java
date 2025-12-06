package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLFormularioEnviosController implements Initializable {

    @FXML
    private ComboBox<?> cbCliente;
    @FXML
    private TextField tfNombreDestinatario;
    @FXML
    private TextField tfApellidoPaternoDestinatario;
    @FXML
    private TextField tfApellidoMaternoDestinatario;
    @FXML
    private TextField tfCodigoPostalDestinatario;
    @FXML
    private ComboBox<?> cbEstadoDestinatario;
    @FXML
    private ComboBox<?> cbCiudadDestinatario;
    @FXML
    private ComboBox<?> cbColoniaDestinatario;
    @FXML
    private TextField tfCalleDestinatario;
    @FXML
    private TextField tfNumeroDestinatario;
    @FXML
    private Button btnCompletar;
    @FXML
    private Label lbTitulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnBuscarCodigoPostal(ActionEvent event) {
    }

    @FXML
    private void btnCrear(ActionEvent event) {
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
}
