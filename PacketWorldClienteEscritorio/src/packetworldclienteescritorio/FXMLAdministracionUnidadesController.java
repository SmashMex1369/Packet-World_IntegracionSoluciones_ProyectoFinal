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
import packetworldclienteescritorio.dominio.UnidadImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLAdministracionUnidadesController implements Initializable, INotificador{

    @FXML
    private TableView<Unidad> tvUnidad;
    @FXML
    private TableColumn tcVIN;
    @FXML
    private TableColumn tcMarca;
    @FXML
    private TableColumn tcModelo;
    @FXML
    private TableColumn tcAnio;
    @FXML
    private TableColumn tcTipoUnidad;
    @FXML
    private TableColumn tcNII;
    @FXML
    private TableColumn tcConductorAsignado;
    @FXML
    private TextField tfBuscar;
    @FXML
    private MenuItem itmHistorialBajas;
    @FXML
    private MenuItem itmBaja;
    
    private ObservableList<Unidad> unidades;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionUnidades();
    }
    
    public void configurarTabla(){
        tcVIN.setCellValueFactory(new PropertyValueFactory("VIN"));
        tcNII.setCellValueFactory(new PropertyValueFactory("NII"));
        tcMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        tcModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        tcAnio.setCellValueFactory(new PropertyValueFactory("año"));//cambiar a año
        tcTipoUnidad.setCellValueFactory(new PropertyValueFactory("tipo"));
        tcConductorAsignado.setCellValueFactory(new PropertyValueFactory("nombre"));
    }
    
    private void cargarInformacionUnidades(){
        HashMap<String, Object> respuesta= UnidadImp.obtenerUnidades();
        boolean esError= (boolean)respuesta.get("error");
        if(!esError){
            List<Unidad> unidadesAPI= (List<Unidad>) respuesta.get(Constantes.KEY_LISTA);
            unidades= FXCollections.observableArrayList();
            unidades.addAll(unidadesAPI);
            tvUnidad.setItems(unidades);
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
        irFormularioUnidades(null);
    }

    @FXML
    private void btnEditar(ActionEvent event) {
    }

    @FXML
    private void btnAsignacion(ActionEvent event) {
    }


    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
    private void irFormularioUnidades(Unidad unidad){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioUnidades.fxml"));
            FXMLFormularioUnidadesController controlador= cargador.getController();
            controlador.iniciarDatos(unidad, this);
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

    @Override
    public void notificarOperacionExitosa(String operacion, String VIN) {
        System.out.println("Operacion: "+operacion+", VIN: "+VIN);
        cargarInformacionUnidades();
    }
}
