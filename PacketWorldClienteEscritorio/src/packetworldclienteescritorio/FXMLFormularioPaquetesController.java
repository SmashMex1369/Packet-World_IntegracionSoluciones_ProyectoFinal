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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.PaqueteImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.NoGuia;
import packetworldclienteescritorio.pojo.Paquete;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLFormularioPaquetesController implements Initializable {

    @FXML
    private ComboBox<NoGuia> cbNoGuia;
    @FXML
    private TextField tfPeso;
    @FXML
    private TextField tfAlto;
    @FXML
    private TextField tfAncho;
    @FXML
    private TextField tfProfundidad;
    @FXML
    private TextArea taDescripcion;
    
    private Paquete paqueteEdicion;
    private ObservableList<NoGuia> envios; 
    private INotificador observador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarEnviosDisponibles();
    }
    
    public void inicializarDatos(Paquete paqueteEdicion, INotificador observador){
        this.paqueteEdicion= paqueteEdicion;
        this.observador=observador;
        if(paqueteEdicion!=null){
            tfPeso.setText(String.valueOf(paqueteEdicion.getPeso()));
            tfAlto.setText(String.valueOf(paqueteEdicion.getAlto()));
            tfAncho.setText(String.valueOf(paqueteEdicion.getAncho()));
            tfProfundidad.setText(String.valueOf(paqueteEdicion.getProfundidad()));
            taDescripcion.setText(paqueteEdicion.getDescripcion());
            int posicionIdEnvio= obtenerPosicionIdEnvio(paqueteEdicion.getIdEnvio());
            cbNoGuia.getSelectionModel().select(posicionIdEnvio);
            cbNoGuia.setDisable(true);
        }
    }
    
    private int obtenerPosicionIdEnvio(int idEnvio){
        for (int i=0; i< envios.size(); i++){
            if(envios.get(i).getIdEnvio()== idEnvio){
                return i;
            }
        }
        return -1;
    }
    
    private boolean sonCamposValidos(){
        boolean camposValidos= true;
        if(tfPeso.getText()!=null || !tfPeso.getText().isEmpty()){
            try {
                if(Float.parseFloat(tfPeso.getText())<=0){
                    camposValidos=false;
                    tfPeso.setStyle("-fx-border-color: #ff0000"); 
                } 
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfPeso.setStyle("-fx-border-color: #ff0000"); 
            }                
        }else{
            camposValidos=false;
            tfPeso.setStyle("-fx-border-color: #ff0000"); 
        }
        if(tfAlto.getText()!=null || !tfAlto.getText().isEmpty()){
            try {
                if(Float.parseFloat(tfAlto.getText())<=0){
                    camposValidos=false;
                    tfAlto.setStyle("-fx-border-color: #ff0000");
                }
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfAlto.setStyle("-fx-border-color: #ff0000");
            }
        }else{
            camposValidos=false;
            tfAlto.setStyle("-fx-border-color: #ff0000");
        }
        if (tfAncho.getText()!=null || !tfAncho.getText().isEmpty()){
            try {
                if(Float.parseFloat(tfAncho.getText())<=0){
                    camposValidos=false;
                    tfAncho.setStyle("-fx-border-color: #ff0000");
                }
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfAncho.setStyle("-fx-border-color: #ff0000");
            }
        }else{
            camposValidos=false;
            tfAncho.setStyle("-fx-border-color: #ff0000");
        }
        if(tfProfundidad.getText()!=null || !tfProfundidad.getText().isEmpty()){
            try {
                if(Float.parseFloat(tfProfundidad.getText())<=0){
                    camposValidos=false;
                    tfProfundidad.setStyle("-fx-border-color: #ff0000");  
                }               
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfProfundidad.setStyle("-fx-border-color: #ff0000");
            }          
        }else{
            camposValidos=false;
            tfProfundidad.setStyle("-fx-border-color: #ff0000");
        }
        if (taDescripcion.getText()==null || taDescripcion.getText().isEmpty()){         
            camposValidos=false;
            taDescripcion.setStyle("-fx-border-color: #ff0000");
            
        }
        if(cbNoGuia.getSelectionModel().getSelectedIndex() == -1){
            camposValidos=false;
            cbNoGuia.setStyle("-fx-border-color: #ff0000");
        }
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes o no tienen el formato adecuado.", Alert.AlertType.ERROR);
        }
        return camposValidos;
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        if (sonCamposValidos()){          
            Paquete paquete= new Paquete();
            paquete.setDescripcion(taDescripcion.getText());
            paquete.setPeso(Float.parseFloat(tfPeso.getText()));
            paquete.setAlto(Float.parseFloat(tfAlto.getText()));
            paquete.setAncho(Float.parseFloat(tfAncho.getText()));
            paquete.setProfundidad(Float.parseFloat(tfProfundidad.getText()));
            NoGuia noGuiaSeleccionado= cbNoGuia.getSelectionModel().getSelectedItem();
            paquete.setIdEnvio(noGuiaSeleccionado.getIdEnvio());
            if(paqueteEdicion==null){
                registrarPaquete(paquete);
            }else{
                editarPaquete(paquete);             
            }
        }
    }
    
    private void cargarEnviosDisponibles(){
        HashMap<String, Object> respuesta= CatalogoImp.obtenerEnviosDisponibles();
        if (!(boolean) respuesta.get(Constantes.KEY_ERROR)){
            List<NoGuia> enviosAPI= (List<NoGuia>) respuesta.get(Constantes.KEY_LISTA);
            envios= FXCollections.observableArrayList();
            envios.addAll(enviosAPI);
            cbNoGuia.setItems(envios);
        }else{
            Utilidades.mostrarAlertaSimple(Constantes.KEY_ERROR, respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            regresarVentana();
        }
    }
    
    private void registrarPaquete(Paquete paquete){
        Respuesta respuesta= PaqueteImp.registrar(paquete);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Paquete registrado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("registro", paquete.getDescripcion());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }    
    }
    
    private void editarPaquete(Paquete paquete){
        paquete.setIdPaquete(paqueteEdicion.getIdPaquete());
        Respuesta respuesta= PaqueteImp.editar(paquete);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Paquete editado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("edición", paquete.getDescripcion());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionPaquetes.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfAlto.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Paquetes");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tfPesoTexto(KeyEvent event) {
        tfPeso.setStyle(null);
    }

    @FXML
    private void cbNoGuiaSeleccion(Event event) {
        cbNoGuia.setStyle(null);
    }

    @FXML
    private void taDescripcionTexto(KeyEvent event) {
        taDescripcion.setStyle(null);
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }

    @FXML
    private void tfAltoTexto(KeyEvent event) {
        tfAlto.setStyle(null);
    }

    @FXML
    private void tfAnchoTexto(KeyEvent event) {
        tfAncho.setStyle(null);
    }

    @FXML
    private void tfProfundidadTexto(KeyEvent event) {
        tfProfundidad.setStyle(null);
    }
    
}
