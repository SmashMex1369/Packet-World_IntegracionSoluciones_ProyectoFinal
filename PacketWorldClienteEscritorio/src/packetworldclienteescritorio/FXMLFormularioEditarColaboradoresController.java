package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLFormularioEditarColaboradoresController implements Initializable {

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
    @FXML
    private ImageView imgvFotoPerfil;
    @FXML
    private Circle circulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnActualizar(ActionEvent event) {
    }

    @FXML
    private void btnSeleccionarImagen(ActionEvent event) {
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
}
