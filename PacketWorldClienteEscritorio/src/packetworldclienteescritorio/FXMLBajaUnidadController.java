package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.UnidadImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Sesion;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLBajaUnidadController implements Initializable {

    @FXML
    private TextArea taMotivo;   
    @FXML
    private CheckBox ckbActivo;
    @FXML
    private Button btnBajas;
    
    private Unidad unidadEdicion;
    private INotificador observador;
    private Colaborador c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c=Sesion.getColaborador();
    }
    
    public void cargarIdUnidad(Unidad unidadEdicion, INotificador observador){
        this.unidadEdicion= unidadEdicion;
        this.observador=observador;
    }

    @FXML
    private void ckbActivo(ActionEvent event) {
        if(ckbActivo.isSelected()){
            taMotivo.setDisable(false);
            btnBajas.setDisable(false);
        }else{
            taMotivo.setDisable(true);
            btnBajas.setDisable(true);
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        ((Stage)taMotivo.getScene().getWindow()).close();
    }

    @FXML
    private void btnBajas(ActionEvent event) {
        if (taMotivo.getText()!=null && !taMotivo.getText().isEmpty()){
            Unidad unidad = new Unidad();
            unidad.setIdUnidad(unidadEdicion.getIdUnidad());
            unidad.setMotivo(taMotivo.getText());
            unidad.setIdColaborador(c.getIdColaborador());   
            UnidadImp.desasignarConductor(unidadEdicion.getIdUnidad());
            darBaja(unidad);
        }else{
            taMotivo.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
            Utilidades.mostrarAlertaSimple("Escriba un motivo", "Para dar de baja una unidad es obligatorio escribir un motivo.", Alert.AlertType.ERROR);
        }
        
        
    }
    
    private void darBaja(Unidad unidad){
        Respuesta respuesta= UnidadImp.darBajaUnidad(unidad);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Baja completada", "La unidad se dio de baja correctamente", Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Baja", unidadEdicion.getVIN());
            ((Stage)taMotivo.getScene().getWindow()).close();
        }else{
            Utilidades.mostrarAlertaSimple("Error al dar de baja", "Hubo un error al dar de baja la unidad", Alert.AlertType.ERROR);
            ((Stage)taMotivo.getScene().getWindow()).close();
        }
    }

    @FXML
    private void tfMotivoTexto(KeyEvent event) {
        taMotivo.setStyle(null);
    }
}
