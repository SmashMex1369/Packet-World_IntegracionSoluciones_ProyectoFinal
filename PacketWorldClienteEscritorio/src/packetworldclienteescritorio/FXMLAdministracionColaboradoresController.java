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
public class FXMLAdministracionColaboradoresController implements Initializable {

    @FXML
    private TableView<?> tvColaboradores;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidoPaterno;
    @FXML
    private TableColumn<?, ?> colApellidoMaterno;
    @FXML
    private TableColumn<?, ?> colCURP;
    @FXML
    private TableColumn<?, ?> colCorreo;
    @FXML
    private TableColumn<?, ?> colNoPersonal;
    @FXML
    private TableColumn<?, ?> colRol;
    @FXML
    private TableColumn<?, ?> colNoLicencia;
    @FXML
    private TableColumn<?, ?> colSucursal;
    @FXML
    private TableView<?> tvAdministradores;
    @FXML
    private TableColumn<?, ?> colNombreAdministradores;
    @FXML
    private TableColumn<?, ?> colApellidoPaternoAdministradores;
    @FXML
    private TableColumn<?, ?> colApellidoMaternoAdministradores;
    @FXML
    private TableColumn<?, ?> colCURPAdministradores;
    @FXML
    private TableColumn<?, ?> colCorreoAdministradores;
    @FXML
    private TableColumn<?, ?> colNoPersonalAdministradores;
    @FXML
    private TableColumn<?, ?> colRolAdministradores;
    @FXML
    private TableColumn<?, ?> colSucursalAdministradores;
    @FXML
    private TableView<?> tvEjecutivos;
    @FXML
    private TableColumn<?, ?> colNombreEjecutivo;
    @FXML
    private TableColumn<?, ?> colApellidoPaternoEjecutivo;
    @FXML
    private TableColumn<?, ?> colApellidoMaternoEjecutivo;
    @FXML
    private TableColumn<?, ?> colCURPEjecutivo;
    @FXML
    private TableColumn<?, ?> colCorreoEjecutivo;
    @FXML
    private TableColumn<?, ?> colNoPersonalEjecutivo;
    @FXML
    private TableColumn<?, ?> colRolEjetutivo;
    @FXML
    private TableColumn<?, ?> colSucursalEjecutivo;
    @FXML
    private TableView<?> tvConductores;
    @FXML
    private TableColumn<?, ?> colNombreConductor;
    @FXML
    private TableColumn<?, ?> colApellidoPaternoConductor;
    @FXML
    private TableColumn<?, ?> colApellidoMaternoConductor;
    @FXML
    private TableColumn<?, ?> colCURPConductor;
    @FXML
    private TableColumn<?, ?> colCorreoConductor;
    @FXML
    private TableColumn<?, ?> colNoPersonalConductor;
    @FXML
    private TableColumn<?, ?> colRolConductor;
    @FXML
    private TableColumn<?, ?> colNoLicenciaConductor;
    @FXML
    private TableColumn<?, ?> colSucursalConductor;
    @FXML
    private TextField tfBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEliminar(ActionEvent event) {
    }

    @FXML
    private void btnEditar(ActionEvent event) {
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
}
