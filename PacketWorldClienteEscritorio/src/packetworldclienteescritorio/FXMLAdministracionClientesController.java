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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.ClienteImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Cliente;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author OmarVX
 */
public class FXMLAdministracionClientesController implements Initializable, INotificador {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn<Cliente, String> colDireccion = new TableColumn<>();
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colCorreo;
    
    private ObservableList<Cliente> clientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInfoClientes();
    }    
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("ApellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("ApellidoMaterno"));
        colDireccion.setCellValueFactory(cellData -> {
        Cliente c = cellData.getValue();
        String combinado = "Calle: " + c.getCalle() + " No." + c.getNumero() + " Colonia: " + c.getColonia();
        return new ReadOnlyStringWrapper(combinado);
        });
        colTelefono.setCellValueFactory(new PropertyValueFactory("Telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("Correo"));
    }
    
    private void cargarInfoClientes(){
        
        HashMap<String, Object> respuesta= ClienteImp.obtenerTodos();
        boolean esError= (boolean) respuesta.get("error");
        if (!esError){
            List<Cliente> clientesAPI= (List<Cliente>)respuesta.get("clientes");
            clientes = FXCollections.observableArrayList();
            clientes.addAll(clientesAPI);
            tvClientes.setItems(clientes);
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
        Cliente cliente= tvClientes.getSelectionModel().getSelectedItem();
        if(cliente!=null){
            boolean confirmarOperacion= Utilidades.mostrarAlertaConfirmacion("Eliminar cliente", "¿Estás seguro de eliminar el cliente?");
            if(confirmarOperacion){
                eliminarCliente(cliente.getIdCliente());
            }
        }else{
             Utilidades.mostrarAlertaSimple("Seleccione un cliente", "Seleccione un cliente para poder eliminarlo.", Alert.AlertType.WARNING);
        }
    }
    
    private void eliminarCliente(int idCliente){
        Respuesta respuesta= ClienteImp.eliminarCliente(idCliente);
        System.out.println("funcion elim"+idCliente);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Cliente eliminado", "El registro del cliente fue eliminado correctamente.", Alert.AlertType.WARNING);
            cargarInfoClientes();
        }else{
            Utilidades.mostrarAlertaSimple("Error al eliminar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicIrEditar(ActionEvent event) {
        Cliente cliente= tvClientes.getSelectionModel().getSelectedItem();
        if(cliente!=null){
            irFormularioClientes(cliente);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione un cliente", "Para editar un cliente debes seleccionar uno", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicIrRegistrar(ActionEvent event) {
        irFormularioClientes(null);
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tvClientes.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Menú principal");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void irFormularioClientes(Cliente cliente){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioClientes.fxml"));
            Parent vista= cargador.load();
            FXMLFormularioClientesController controlador= cargador.getController();
            controlador.inicializarDatosClientes(cliente, this);
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tvClientes.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Formulario clientes");  
            //escenario.initModality(Modality.APPLICATION_MODAL);            
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void notificarOperacionExitosa(String operacion, String descripcion){
        System.out.println("Operacion: "+operacion+", descripcion del profesor: "+descripcion);
        cargarInfoClientes();
    }
    
}
