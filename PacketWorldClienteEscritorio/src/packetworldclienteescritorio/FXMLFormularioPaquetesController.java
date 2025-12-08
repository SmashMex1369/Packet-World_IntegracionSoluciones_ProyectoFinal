package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
            Utilidades.mostrarAlertaSimple("Error", respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            cerrarVentana();
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void registrarPaquete(Paquete paquete){
        Respuesta respuesta= PaqueteImp.registrar(paquete);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Paquete registrado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("registro", paquete.getDescripcion());
            cerrarVentana();
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
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        ((Stage)taDescripcion.getScene().getWindow()).close();
    }

    
}
