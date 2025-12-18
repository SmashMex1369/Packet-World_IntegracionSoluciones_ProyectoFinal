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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.ColaboradorImp;
import packetworldclienteescritorio.dominio.EnvioImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Conductor;
import packetworldclienteescritorio.pojo.Envio;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLAsignarConductorEnvioController implements Initializable {

    @FXML
    private TableView<Conductor> tvConductores;
    @FXML
    private TableColumn colNoPersonal;
    @FXML
    private TableColumn colNoLicencia;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TextField tfBuscar;
    
    private Envio envio;
    private INotificador observador;
    private ObservableList<Conductor> conductores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
    }    
    
    private void configurarTabla(){
        colNoPersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colNoLicencia.setCellValueFactory(new PropertyValueFactory("noLicencia"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
    }
    
    
    

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }

    @FXML
    private void btnAsignar(ActionEvent event) {
        Conductor conductor = tvConductores.getSelectionModel().getSelectedItem();
        if(conductor!=null){
            asignarConductor(conductor.getIdConductor());
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione un conductor", "Para asignar un conductor, debe seleccionar uno", Alert.AlertType.WARNING);
        }
    }
    
    public void cargarInformacionConductores(Envio envio, INotificador observador){
        this.envio = envio;
        this.observador = observador;
        HashMap<String, Object> respuesta = null;
        if(tfBuscar.getText().isEmpty()){
            respuesta = ColaboradorImp.obtenerConductoresSucursal(this.envio.getIdSucursal());
        }else{
            respuesta = ColaboradorImp.buscarConductoresSucursal(this.envio.getIdSucursal(), tfBuscar.getText());
        }
        if(!(boolean) respuesta.get(Constantes.KEY_ERROR)){
            List<Conductor> conductoresAPI = (List<Conductor>) (respuesta.get(Constantes.KEY_LISTA));
            conductores = FXCollections.observableArrayList();
            conductores.addAll(conductoresAPI);
            tvConductores.setItems(conductores);
        }
    }
    
    private void asignarConductor(Integer idConductor){
        Integer idEnvio = envio.getIdEnvio();
        Respuesta respuesta = EnvioImp.asignarConductor(idConductor, idEnvio);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Conductor asignado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Asignar", ""+idConductor);
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al asignar", respuesta.getMensaje(), Alert.AlertType.ERROR);
            regresarVentana();
        }
    }

    @FXML
    private void tfBuscarTexto(KeyEvent event) {
        cargarInformacionConductores(envio, observador);
    }
    
    private void regresarVentana(){
        Stage escenario = (Stage) tvConductores.getScene().getWindow();
        escenario.close();
    }
}
