package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private Label lbSaludo;
    @FXML
    private ImageView ivFoto;
    @FXML
    private Label lbTipoRol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void irAdmUnidades(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionUnidades.fxml"));
            Parent vista= cargador.load();
            FXMLAdministracionUnidadesController controlador= cargador.getController();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) lbSaludo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Unidades");
            escenario.show();
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnEnvios(ActionEvent event) {
    }

    @FXML
    private void btnPaquetes(ActionEvent event) {
    }

    @FXML
    private void btnClientes(ActionEvent event) {
    }

    @FXML
    private void btnColaboradores(ActionEvent event) {
    }

    @FXML
    private void btnUnidades(ActionEvent event) {
        irAdmUnidades();
    }

    @FXML
    private void btnSucursales(ActionEvent event) {
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
}
