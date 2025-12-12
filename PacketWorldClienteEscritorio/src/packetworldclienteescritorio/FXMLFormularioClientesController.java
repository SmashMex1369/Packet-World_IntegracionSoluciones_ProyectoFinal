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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.ClienteImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Cliente;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author OmarVX
 */
public class FXMLFormularioClientesController implements Initializable {

    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfNumeroExterior;
    @FXML
    private TextField tfCalle;
    @FXML
    private ComboBox<Cliente> cbEstado;
    @FXML
    private ComboBox<Cliente> cbCiudad;
    @FXML
    private ComboBox<Cliente> cbColonia;
    
    private Cliente clienteEdicion;
    private INotificador observador;
    private ObservableList <Cliente> cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //cargarClientes();
    }    
    
    public void inicializarDatosClientes(Cliente clienteEdicion, INotificador observador){
        this.clienteEdicion = clienteEdicion;
        this.observador = observador;
        if(clienteEdicion != null){
            tfNombre.setText(clienteEdicion.getNombre());
            tfApellidoPaterno.setText(clienteEdicion.getApellidoPaterno());
            tfApellidoMaterno.setText(clienteEdicion.getApellidoMaterno());
            tfTelefono.setText(clienteEdicion.getTelefono());
            tfCorreo.setText(clienteEdicion.getCorreo());
            tfCodigoPostal.setText(String.valueOf(clienteEdicion.getCodigoPostal()));
            tfCalle.setText(clienteEdicion.getCalle());
            tfNumeroExterior.setText(String.valueOf(clienteEdicion.getNumero()));
        }
    }
    
    private boolean sonCamposValidos(){
        boolean camposValidos= true;
        return camposValidos;
    }

    @FXML
    private void clicBtnGuardar(ActionEvent event) {
        if(sonCamposValidos()){
            Cliente cliente = new Cliente();
            cliente.setNombre(tfNombre.getText());
            cliente.setApellidoPaterno(tfApellidoPaterno.getText());
            cliente.setApellidoMaterno(tfApellidoMaterno.getText());
            cliente.setCorreo(tfCorreo.getText());
            cliente.setTelefono(tfTelefono.getText());
            cliente.setCalle(tfCalle.getText());
            cliente.setNumero(Integer.parseInt(tfNumeroExterior.getText()));
            cliente.setCodigoPostal(Integer.parseInt(tfCodigoPostal.getText()));
            if(clienteEdicion==null){
                registrarCliente(cliente);
            }else{
                editarCliente(cliente);             
            }
        }
    }

    @FXML
    private void clicBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }
    
    private void cargarClientes(){
        HashMap<String, Object> respuesta= ClienteImp.obtenerTodos();
        if (!(boolean) respuesta.get(Constantes.KEY_ERROR)){
            List<Cliente> clienteAPI= (List<Cliente>) respuesta.get(Constantes.KEY_LISTA);
            cliente = FXCollections.observableArrayList();
            cliente.addAll(clienteAPI);
            cbCiudad.setItems(cliente);
        }else{
            Utilidades.mostrarAlertaSimple("Error", respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            regresarVentana();
        }
    }
    
    private void registrarCliente(Cliente cliente){
        Respuesta respuesta= ClienteImp.registrar(cliente);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Cliente registrado con éxito", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("registro", cliente.getNombre());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }    
    }
    
    private void editarCliente(Cliente cliente){
        cliente.setIdCliente(clienteEdicion.getIdCliente());
        Respuesta respuesta= ClienteImp.editar(cliente);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Cliente actualizado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("edición", cliente.getNombre());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionClientes.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfCorreo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Clientes");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
