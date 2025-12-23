package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.UnidadImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Conductor;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLAsociacionUnidadConductorController implements Initializable {

    @FXML
    private TextField tfBuscarCond;
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
    
    private Unidad unidad;
    private INotificador observador;
    private ObservableList<Conductor> conductores;
    @FXML
    private Button btnAsignar;
    @FXML
    private Button btnCambiar;
    @FXML
    private Button btnDesasignar;

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
    
    public void cargarInfConductoresSinUnidad(Unidad unidad, INotificador observador){
        this.unidad= unidad;
        this.observador=observador;
        HashMap<String, Object> respuesta;
        if(tfBuscarCond.getText().isEmpty()){
            respuesta= UnidadImp.conductoresSinUnidad();
        }else{
            respuesta= UnidadImp.buscarConductor(tfBuscarCond.getText());
        }
        boolean esError= (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!esError){
            List<Conductor> conductoresAPI= (List<Conductor>)respuesta.get(Constantes.KEY_LISTA);
            conductores= FXCollections.observableArrayList();
            conductores.addAll(conductoresAPI);
            tvConductores.setItems(conductores);
        }
        configurarBotones();
    }
    
    private void configurarBotones(){
        if(unidad.getIdConductor()==0){
            btnCambiar.setDisable(true);
            btnDesasignar.setDisable(true);
        }else{
            btnAsignar.setDisable(true);
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }

    @FXML
    private void buscarConductor(ActionEvent event) {
    }

    @FXML
    private void btnAsignar(ActionEvent event) {
        Conductor conductor= tvConductores.getSelectionModel().getSelectedItem();
        if(conductor!=null){
            asignarUnidadConductor(conductor.getIdConductor());
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione un conductor", "Para asignar un conductor, debe seleccionar uno", Alert.AlertType.WARNING);
        }
    }
    
    private void asignarUnidadConductor(Integer idConductor){
        Integer idUnidad= unidad.getIdUnidad();
        Respuesta respuesta= UnidadImp.asignarUnidadConductor(idConductor, idUnidad);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Unidad asignada al conductor", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Asignar", ""+idConductor);
            regresarVentana();
        }
    }
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionUnidades.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfBuscarCond.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración unidades");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCambiar(ActionEvent event) {
        Conductor conductor= tvConductores.getSelectionModel().getSelectedItem();
        if(conductor!=null){
            asignarUnidadConductor(conductor.getIdConductor());
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione un conductor", "Para cambiar un conductor, debe seleccionar uno", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnDesasignar(ActionEvent event) {
        desasignarConductor();
    }
    
    private void desasignarConductor(){
        Integer idUnidad=unidad.getIdUnidad();
        Respuesta respuesta= UnidadImp.desasignarConductor(idUnidad);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Conductor desasignado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Desasignar", ""+idUnidad);
            regresarVentana();
        }
    }

    @FXML
    private void tfBuscar(KeyEvent event) {
        cargarInfConductoresSinUnidad(unidad, observador);
    }
    
}
