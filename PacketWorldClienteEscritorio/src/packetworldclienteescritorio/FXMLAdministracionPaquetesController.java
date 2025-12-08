package packetworldclienteescritorio;

import java.io.IOException;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.PaqueteImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Paquete;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLAdministracionPaquetesController implements Initializable, INotificador {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<Paquete> tvPaquete;
    @FXML
    private TableColumn tcNoGuia;
    @FXML
    private TableColumn tcDescripcion;
    @FXML
    private TableColumn tcPeso;
    @FXML
    private TableColumn tcAlto;
    @FXML
    private TableColumn tcAncho;
    @FXML
    private TableColumn tcProfundidad;
    
    private ObservableList<Paquete> paquetes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionPaquetes();
    }
    
    private void configurarTabla(){
        tcDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        tcPeso.setCellValueFactory(new PropertyValueFactory("peso"));
        tcAlto.setCellValueFactory(new PropertyValueFactory("alto"));
        tcAncho.setCellValueFactory(new PropertyValueFactory("ancho"));
        tcProfundidad.setCellValueFactory(new PropertyValueFactory("profundidad"));
    }
    
    private void cargarInformacionPaquetes(){
        HashMap<String, Object> respuesta= PaqueteImp.obtenerTodos();
        boolean esError= (boolean) respuesta.get("error");
        if (!esError){
            List<Paquete> paquetesAPI= (List<Paquete>)respuesta.get("paquetes");
            paquetes= FXCollections.observableArrayList();
            paquetes.addAll(paquetesAPI);
            tvPaquete.setItems(paquetes);
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
        irFormularioPaquetes(null);
    }

    @FXML
    private void btnEditar(ActionEvent event) {
        Paquete paquete= tvPaquete.getSelectionModel().getSelectedItem();
        if(paquete!=null){
            irFormularioPaquetes(paquete);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione un paquete", "Para editar un paquete debes seleccionar uno", Alert.AlertType.WARNING);
        }
    }


    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
    private void irFormularioPaquetes(Paquete paquete){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioPaquetes.fxml"));
            Parent vista= cargador.load();
            FXMLFormularioPaquetesController controlador= cargador.getController();
            controlador.inicializarDatos(paquete, this);
            Scene escena= new Scene(vista);
            Stage escenario= new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Formulario paquetes");            
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void notificarOperacionExitosa(String operacion, String descripcion){
        System.out.println("Operacion: "+operacion+", descripcion del profesor: "+descripcion);
        cargarInformacionPaquetes();
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
    }
}
