package packetworldclienteescritorio;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.ColaboradorImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.pojo.Conductor;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLAdministracionColaboradoresController implements Initializable, INotificador {

    @FXML
    private TableView<Conductor> tvColaboradores;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colCURP;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colNoPersonal;
    @FXML
    private TableColumn colRol;
    @FXML
    private TableColumn colNoLicencia;
    @FXML
    private TableColumn colSucursal;
    @FXML
    private TableView<Conductor> tvAdministradores;
    @FXML
    private TableColumn colNombreAdministradores;
    @FXML
    private TableColumn colApellidoPaternoAdministradores;
    @FXML
    private TableColumn colApellidoMaternoAdministradores;
    @FXML
    private TableColumn colCURPAdministradores;
    @FXML
    private TableColumn colCorreoAdministradores;
    @FXML
    private TableColumn colNoPersonalAdministradores;
    @FXML
    private TableColumn colRolAdministradores;
    @FXML
    private TableColumn colSucursalAdministradores;
    @FXML
    private TableView<Conductor> tvEjecutivos;
    @FXML
    private TableColumn colNombreEjecutivo;
    @FXML
    private TableColumn colApellidoPaternoEjecutivo;
    @FXML
    private TableColumn colApellidoMaternoEjecutivo;
    @FXML
    private TableColumn colCURPEjecutivo;
    @FXML
    private TableColumn colCorreoEjecutivo;
    @FXML
    private TableColumn colNoPersonalEjecutivo;
    @FXML
    private TableColumn colRolEjetutivo;
    @FXML
    private TableColumn colSucursalEjecutivo;
    @FXML
    private TableView<Conductor> tvConductores;
    @FXML
    private TableColumn colNombreConductor;
    @FXML
    private TableColumn colApellidoPaternoConductor;
    @FXML
    private TableColumn colApellidoMaternoConductor;
    @FXML
    private TableColumn colCURPConductor;
    @FXML
    private TableColumn colCorreoConductor;
    @FXML
    private TableColumn colNoPersonalConductor;
    @FXML
    private TableColumn colRolConductor;
    @FXML
    private TableColumn colNoLicenciaConductor;
    @FXML
    private TableColumn colSucursalConductor;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Tab tabTodos;
    @FXML
    private Tab tabAdministradores;
    @FXML
    private Tab tabEjecutivos;
    @FXML
    private Tab tabConductores;
    @FXML
    private TabPane tpColaboradores;
    
    private ObservableList<Conductor> colaboradores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTablaColaboradores();
        cargarInformacionColaboradores();
        agregarListenersATabs();
    }    

    @FXML
    private void btnRegistrar(ActionEvent event) {
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioColaboradores.fxml"));
            Parent vista= cargador.load();
            FXMLFormularioColaboradoresController controlador= cargador.getController();
            controlador.observador(this);
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tpColaboradores.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Formulario Colaboradores");             
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEditar(ActionEvent event) {
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
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

    
    
    private void agregarListenersATabs(){
        Platform.runLater(() -> {
            tpColaboradores.applyCss();
            tpColaboradores.layout();

            ObservableList<Tab> tabs = tpColaboradores.getTabs();
            List<Node> tabNodes = new ArrayList<>(tpColaboradores.lookupAll(".tab"));

            for (int i = 0; i < tabNodes.size(); i++) {
                Node tabNode = tabNodes.get(i);
                Tab tab = tabs.get(i);

                tabNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                    switch(tab.getId()){
                        case "tabTodos":
                            configurarTablaColaboradores();
                            cargarInformacionColaboradores();
                            tabTodos.setStyle("-fx-base: #06a1df; -fx-border-width: 5 5 0 5; -fx-border-color: #03658c; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; -fx-padding: 2 0 2 0;");
                            tabAdministradores.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabEjecutivos.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabConductores.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            break;
                        case "tabAdministradores":
                            configurarTablaAdministradores();
                            cargarInformacionColaboradoresRol(1, tab);
                            tabAdministradores.setStyle("-fx-base: #06a1df; -fx-border-width: 5 5 0 5; -fx-border-color: #03658c; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; -fx-padding: 2 0 2 0;");
                            tabTodos.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabEjecutivos.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabConductores.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            break;
                        case "tabEjecutivos":
                            configurarTablaEjecutivos();
                            cargarInformacionColaboradoresRol(2, tab);
                            tabEjecutivos.setStyle("-fx-base: #06a1df; -fx-border-width: 5 5 0 5; -fx-border-color: #03658c; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; -fx-padding: 2 0 2 0;");
                            tabTodos.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabAdministradores.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabConductores.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            break;
                        case "tabConductores":
                            configurarTablaConductores();
                            cargarInformacionColaboradoresRol(3, tab);
                            tabConductores.setStyle("-fx-base: #06a1df; -fx-border-width: 5 5 0 5; -fx-border-color: #03658c; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; -fx-padding: 2 0 2 0;");
                            tabTodos.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabAdministradores.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            tabEjecutivos.setStyle("-fx-base: #03658c; -fx-background-radius: 10 10 0 0;");
                            break;
                    }
                });
            }
        });

    }
    
    private void configurarTablaColaboradores(){
        colNoPersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colRol.setCellValueFactory(new PropertyValueFactory("rol"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colSucursal.setCellValueFactory(new PropertyValueFactory("CUS"));
        colNoLicencia.setCellValueFactory(new PropertyValueFactory("noLicencia"));
        colCURP.setCellValueFactory(new PropertyValueFactory("CURP"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    private void configurarTablaAdministradores(){
        colNoPersonalAdministradores.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colRolAdministradores.setCellValueFactory(new PropertyValueFactory("rol"));
        colNombreAdministradores.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaternoAdministradores.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaternoAdministradores.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colSucursalAdministradores.setCellValueFactory(new PropertyValueFactory("CUS"));
        colCURPAdministradores.setCellValueFactory(new PropertyValueFactory("CURP"));
        colCorreoAdministradores.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    private void configurarTablaEjecutivos(){
        colNoPersonalEjecutivo.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colRolEjetutivo.setCellValueFactory(new PropertyValueFactory("rol"));
        colNombreEjecutivo.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaternoEjecutivo.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaternoEjecutivo.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colSucursalEjecutivo.setCellValueFactory(new PropertyValueFactory("CUS"));
        colCURPEjecutivo.setCellValueFactory(new PropertyValueFactory("CURP"));
        colCorreoEjecutivo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    private void configurarTablaConductores(){
        colNoPersonalConductor.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colRolConductor.setCellValueFactory(new PropertyValueFactory("rol"));
        colNombreConductor.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaternoConductor.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaternoConductor.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colSucursalConductor.setCellValueFactory(new PropertyValueFactory("CUS"));
        colNoLicenciaConductor.setCellValueFactory(new PropertyValueFactory("noLicencia"));
        colCURPConductor.setCellValueFactory(new PropertyValueFactory("CURP"));
        colCorreoConductor.setCellValueFactory(new PropertyValueFactory("correo"));
    }

    @FXML
    private void perderFoco(MouseEvent event) {
        tpColaboradores.getParent().requestFocus();
        tvAdministradores.getSelectionModel().clearSelection();
        tvColaboradores.getSelectionModel().clearSelection();
        tvConductores.getSelectionModel().clearSelection();
        tvEjecutivos.getSelectionModel().clearSelection();
    }
    
    private void cargarInformacionColaboradores(){
        HashMap<String, Object> respuesta;
        if (tfBuscar.getText().isEmpty()) {
            respuesta = ColaboradorImp.obtenerColaboradores();
        }else{
            respuesta = ColaboradorImp.buscarColaboradores(tfBuscar.getText());
        }
        boolean esError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!esError){
            List<Conductor> colaboradoresAPI = (List<Conductor>) respuesta.get(Constantes.KEY_LISTA);
            colaboradores = FXCollections.observableArrayList();
            colaboradores.addAll(colaboradoresAPI);
            tvColaboradores.setItems(colaboradores);
            
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }
    
    private void cargarInformacionColaboradoresRol(Integer idRol, Tab tab){
        HashMap<String, Object> respuesta;
        if (tfBuscar.getText().isEmpty()) {
            respuesta = ColaboradorImp.obtenerColaboradoresRol(idRol);
        }else{
            respuesta = ColaboradorImp.buscarColaboradoresRol(idRol, tfBuscar.getText());
        }
        boolean esError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if(!esError){
            List<Conductor> colaboradoresAPI = (List<Conductor>) respuesta.get(Constantes.KEY_LISTA);
            colaboradores = FXCollections.observableArrayList();
            colaboradores.addAll(colaboradoresAPI);
            switch (tab.getId()){
                case "tabAdministradores":
                    tvAdministradores.setItems(colaboradores);
                    break;
                case "tabEjecutivos":
                    tvEjecutivos.setItems(colaboradores);
                    break;
                case "tabConductores":
                    tvConductores.setItems(colaboradores);
                    break;
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void tfBuscarTexto(KeyEvent event) {
        if(tpColaboradores.getSelectionModel().getSelectedIndex()==0){
            cargarInformacionColaboradores();
        }else{
            cargarInformacionColaboradoresRol(tpColaboradores.getSelectionModel().getSelectedIndex(), tpColaboradores.getSelectionModel().getSelectedItem());
        }
    }

    @Override
    public void notificarOperacionExitosa(String operacion, String nombre) {
        System.out.println("Operacion: "+ operacion + ", NoPersonal: " + nombre);
        cargarInformacionColaboradores();
    }
    
}
