package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLFormularioColaboradoresController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCURP;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private ComboBox<?> cbSucursal;
    @FXML
    private ComboBox<?> cbRol;
    @FXML
    private TextField tfContraseña;
    @FXML
    private TextField tfNoLicencia;

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
    private void btnRegistrar(ActionEvent event) {
    }
    
}
