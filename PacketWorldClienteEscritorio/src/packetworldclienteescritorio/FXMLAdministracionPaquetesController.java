package packetworldclienteescritorio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLAdministracionPaquetesController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<?> tvPaquete;
    @FXML
    private TableColumn<?, ?> tcNoGuia;
    @FXML
    private TableColumn<?, ?> tcDescripcion;
    @FXML
    private TableColumn<?, ?> tcPeso;
    @FXML
    private TableColumn<?, ?> tcAlto;
    @FXML
    private TableColumn<?, ?> tcAncho;
    @FXML
    private TableColumn<?, ?> tcProfundidad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegistrar(ActionEvent event) {
        irFormularioPaquetes();
    }

    @FXML
    private void btnEditar(ActionEvent event) {
    }


    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
    private void irFormularioPaquetes(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioPaquetes.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Formulario paquetes");            
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
    }
}
