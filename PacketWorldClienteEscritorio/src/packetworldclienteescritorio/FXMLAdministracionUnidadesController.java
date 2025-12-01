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
public class FXMLAdministracionUnidadesController implements Initializable {

    @FXML
    private TableView<?> tvUnidad;
    @FXML
    private TableColumn<?, ?> tcVIN;
    @FXML
    private TableColumn<?, ?> tcMarca;
    @FXML
    private TableColumn<?, ?> tcModelo;
    @FXML
    private TableColumn<?, ?> tcAnio;
    @FXML
    private TableColumn<?, ?> tcTipoUnidad;
    @FXML
    private TableColumn<?, ?> tcNII;
    @FXML
    private TableColumn<?, ?> tcConductorAsignado;
    @FXML
    private TextField tfBuscar;
    @FXML
    private MenuItem itmHistorialBajas;
    @FXML
    private MenuItem itmBaja;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegistrar(ActionEvent event) {
        irFormularioUnidades();
    }

    @FXML
    private void btnEditar(ActionEvent event) {
    }

    @FXML
    private void btnAsignacion(ActionEvent event) {
    }

    @FXML
    private void btnOpcionesBaja(ActionEvent event) {
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
    private void irFormularioUnidades(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioUnidades.fxml"));
            //FXMLFormularioUnidadesController controlador= cargador.getController();
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Formulario unidades");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
