package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import packetworldclienteescritorio.dominio.EnvioImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Envio;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLAdministracionEnviosController implements Initializable, INotificador {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<Envio> tvEnvios;
    @FXML
    private TableColumn colNoGuia;
    @FXML
    private TableColumn<Envio, String> colNombreDestinatario = new TableColumn<>();
    @FXML
    private TableColumn colEstado;
    @FXML
    private TableColumn colCodigoPostal;
    @FXML
    private TableColumn<Envio, String> colConductor = new TableColumn<>();
    
    private ObservableList<Envio> envios;
    @FXML
    private AnchorPane apFondo;
    @FXML
    private TableColumn colNoPaquetes;
    @FXML
    private TableColumn colCosto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionEnvios();
    }    
    
    private void configurarTabla(){
        colNoGuia.setCellValueFactory(new PropertyValueFactory("noGuia"));
        colNombreDestinatario.setCellValueFactory(cellData ->{
            Envio e = cellData.getValue();
            String datos = e.getNombreDest() + " " + e.getApellidoPatDest() + " " + e.getApellidoMatDest();
            return new ReadOnlyStringWrapper(datos);
        });
        colEstado.setCellValueFactory(new PropertyValueFactory("estadoDest"));
        colCodigoPostal.setCellValueFactory(new PropertyValueFactory("codigoPostalDest"));
        colConductor.setCellValueFactory(cellData -> {
            Envio e = cellData.getValue();
            String datos = e.getNombreConductor() + " " + e.getApellidoPatConductor() + " " + e.getApellidoMatConductor();
            return new ReadOnlyStringWrapper(datos);
        });
    }
    
    private void cargarInformacionEnvios(){
        HashMap<String, Object> respuesta = null;
        if (tfBuscar.getText().isEmpty()) {
            respuesta = EnvioImp.obtenerEnvios();
        }else{
            respuesta = EnvioImp.buscarEnvio(tfBuscar.getText());
        }
        boolean esError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!esError) {
            List<Envio> enviosAPI = (List<Envio>) respuesta.get(Constantes.KEY_LISTA);
            for(int i=0;i<enviosAPI.size();i++){
                if(enviosAPI.get(i).getIdConductor()==0){
                    enviosAPI.get(i).setNombreConductor("");
                    enviosAPI.get(i).setApellidoPatConductor("");
                    enviosAPI.get(i).setApellidoMatConductor("");
                }
                if(enviosAPI.get(i).getApellidoMatDest()==null){
                    enviosAPI.get(i).setApellidoMatDest("");
                }
                if (enviosAPI.get(i).getApellidoMatConductor()==null){
                    enviosAPI.get(i).setApellidoMatConductor("");
                }
            }
            envios = FXCollections.observableArrayList();
            envios.addAll(enviosAPI);
            tvEnvios.setItems(envios);
        }else{
            Utilidades.mostrarAlertaSimple("Error al carga", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfBuscar.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Menú Principal");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCrear(ActionEvent event) {
        irFormularioEnvios();
    }

    @FXML
    private void btnAsignarConductor(ActionEvent event) {
        Envio envio = tvEnvios.getSelectionModel().getSelectedItem();
        if (envio!=null) {
            if (envio.getIdConductor()==0) {
                irAsignacionConductor(envio);    
            }else{
                Utilidades.mostrarAlertaSimple("No es posible asignar", "El envio seleccionado ya cuenta con un conductor asignado, seleccione en envio diferente", Alert.AlertType.WARNING);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione un envio", "Para asignar un conductor, debe seleccionar un envio sin conductor asignado", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnConsultarDetalles(ActionEvent event) {
        Envio envio = tvEnvios.getSelectionModel().getSelectedItem();
        if(envio!=null){
            irDetallesEnvio(envio);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione un envio", "Para consultar los detalles, debes seleccionar un envio.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void buscarEnvio(KeyEvent event) {
        cargarInformacionEnvios();
    }

    @FXML
    private void perderFoco(MouseEvent event) {
        tvEnvios.getParent().requestFocus();
        tvEnvios.getSelectionModel().clearSelection();
    }
    
    private void irFormularioEnvios(){
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioEnvios.fxml"));
            Parent vista = cargador.load();
            FXMLFormularioEnviosController controlador = cargador.getController();
            controlador.inicializarDatos(null, this);
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tvEnvios.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Crear Envio");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void irDetallesEnvio(Envio envio){
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLDetallesEnvio.fxml"));
            Parent vista = cargador.load();
            FXMLDetallesEnvioController controlador = cargador.getController();
            controlador.inicializarDatos(envio, this);
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) tvEnvios.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Detalles Envio");
            escenario.show();
            escenario.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void irAsignacionConductor(Envio envio){
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLAsignarConductorEnvio.fxml"));
            Parent vista = cargador.load();
            FXMLAsignarConductorEnvioController controlador = cargador.getController();
            controlador.cargarInformacionConductores(envio, this);
            Scene escena = new Scene(vista);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.setResizable(false);
            escenario.setTitle("Asignacion Conductor");
            escenario.initStyle(StageStyle.DECORATED);
            escenario.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notificarOperacionExitosa(String operacion, String descripcion) {
        System.out.println("Operacion: "+operacion+", Descripcion: "+descripcion);
        cargarInformacionEnvios();
    }
    
}
