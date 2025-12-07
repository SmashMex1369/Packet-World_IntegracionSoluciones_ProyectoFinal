package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLActualizarEstatusController implements Initializable {

    @FXML
    private Label lbMotivo;
    @FXML
    private ComboBox<?> cbEstatus;
    @FXML
    private TextArea taMotivo;

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
    private void btnActualizar(ActionEvent event) {
    }
    
}
