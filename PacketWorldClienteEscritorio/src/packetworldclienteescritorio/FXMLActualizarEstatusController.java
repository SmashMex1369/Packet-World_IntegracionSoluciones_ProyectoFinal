package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.EnvioImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.pojo.Envio;
import packetworldclienteescritorio.pojo.EstatusEnvio;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Sesion;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLActualizarEstatusController implements Initializable {

    @FXML
    private Label lbMotivo;
    @FXML
    private ComboBox<EstatusEnvio> cbEstatus;
    @FXML
    private TextArea taMotivo;
    
    private Envio envioEdicion;
    private INotificador observador;
    private Colaborador c;
    private ObservableList<EstatusEnvio> estatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c = Sesion.getColaborador();
        cargarEstatusEnvio();
    }    
    
    public void cargarIdEnvio(Envio envioEdicion, INotificador observador){
        this.envioEdicion = envioEdicion;
        this.observador = observador;
        cbEstatus.getSelectionModel().select(obtenerPosicionEstatus(envioEdicion.getIdEstatusEnvio()));
        if (cbEstatus.getSelectionModel().getSelectedIndex()==3 || 
                cbEstatus.getSelectionModel().getSelectedIndex()==5) {
            lbMotivo.setText("Detalles / Motivo (Obligatorio)");
        }else{
            lbMotivo.setText("Detalles / Motivo (Opcional)");
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        Stage escenario = (Stage) cbEstatus.getScene().getWindow();
        escenario.close();
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        
        if(sonCamposValidos()){
            boolean confirmarOperacion = Utilidades.mostrarAlertaConfirmacion("Actualizar estatus", "¿Esta seguro de actualizar el estatus de este envio?");
            if (confirmarOperacion) {
                Envio envio = new Envio();
                envio.setIdEnvio(envioEdicion.getIdEnvio());
                EstatusEnvio estatusSeleccionado = cbEstatus.getSelectionModel().getSelectedItem();
                envio.setIdEstatusEnvio(estatusSeleccionado.getIdEstatusEnvio());
                envio.setIdColaborador(c.getIdColaborador());
                envio.setMotivo(taMotivo.getText());
                actualizarEstatusEnvio(envio);
            }
            
        }
    }
    
    private void actualizarEstatusEnvio(Envio envio){
        Respuesta respuesta = EnvioImp.actualizarEstatusEnvio(envio);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlertaSimple("Estatus actualizado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Actualizacion", envioEdicion.getNoGuia());
            Stage escenario = (Stage) cbEstatus.getScene().getWindow();
            escenario.close();
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
        
    }
    
    private boolean sonCamposValidos(){
        boolean camposValidos = true;
        if(cbEstatus.getSelectionModel().getSelectedIndex()==-1){
            camposValidos = false;
            cbEstatus.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if ((cbEstatus.getSelectionModel().getSelectedIndex()==3 || 
                cbEstatus.getSelectionModel().getSelectedIndex()==5)&&
                (taMotivo.getText()==null || taMotivo.getText().isEmpty())) {
            camposValidos = false;
            taMotivo.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes, favor de completarlos.", Alert.AlertType.ERROR);
        }
        return camposValidos;
    }
    
    private void cargarEstatusEnvio(){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerEstatusEnvio();
        if(!(boolean) respuesta.get(Constantes.KEY_ERROR)){
            List<EstatusEnvio> estatusAPI = (List<EstatusEnvio>) respuesta.get(Constantes.KEY_LISTA);
            estatus = FXCollections.observableArrayList();
            estatus.addAll(estatusAPI);
            cbEstatus.setItems(estatus);
        }else{
            Utilidades.mostrarAlertaSimple("Error", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }
    
    private int obtenerPosicionEstatus(int idEstatusEnvio){
        for(int i = 0;i<estatus.size();i++){
            if(estatus.get(i).getIdEstatusEnvio()==idEstatusEnvio){
                return i;
            }
        }
        return -1;
    }

    @FXML
    private void cbEstatusSeleccion(Event event) {
        if (cbEstatus.getSelectionModel().getSelectedIndex()==3 || 
                cbEstatus.getSelectionModel().getSelectedIndex()==5) {
            lbMotivo.setText("Detalles / Motivo (Obligatorio)");
        }else{
            lbMotivo.setText("Detalles / Motivo (Opcional)");
        }
    }

    @FXML
    private void perderFoco(MouseEvent event) {
        taMotivo.getParent().requestFocus();
    }
    
}
