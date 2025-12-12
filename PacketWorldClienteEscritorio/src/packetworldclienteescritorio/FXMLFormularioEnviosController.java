package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Direccion;
import packetworldclienteescritorio.pojo.Envio;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLFormularioEnviosController implements Initializable{

    @FXML
    private ComboBox<?> cbCliente;
    @FXML
    private TextField tfNombreDestinatario;
    @FXML
    private TextField tfApellidoPaternoDestinatario;
    @FXML
    private TextField tfApellidoMaternoDestinatario;
    @FXML
    private TextField tfCodigoPostalDestinatario;
    @FXML
    private ComboBox<Direccion> cbEstadoDestinatario;
    @FXML
    private ComboBox<Direccion> cbCiudadDestinatario;
    @FXML
    private ComboBox<Direccion> cbColoniaDestinatario;
    @FXML
    private TextField tfCalleDestinatario;
    @FXML
    private TextField tfNumeroDestinatario;
    @FXML
    private Button btnCompletar;
    @FXML
    private Label lbTitulo;
    
    private Envio envioEdicion;
    private INotificador observador;
    
    //private ObservableList<NombreClientes> clientes;
    private ObservableList<Direccion> colonias;
    private ObservableList<Direccion> ciudad;
    private ObservableList<Direccion> estado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarDatos(Envio envioEdicion, INotificador observador){
        this.envioEdicion = envioEdicion;
        this.observador=observador;
        if (envioEdicion != null){
            //cbCliente
        }
    }
    
    private void cargarClientes(){
        
    }
    
    private boolean cargarColonias(int codigoPostal){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerColonias(codigoPostal);
        boolean esError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!esError) {
            List<Direccion> coloniasAPI = (List<Direccion>) respuesta.get(Constantes.KEY_LISTA);
            ObservableList<Direccion> datos = FXCollections.observableArrayList(coloniasAPI);

            colonias = datos.filtered(d -> d.getColonia() != null);
            ciudad = datos.filtered(d -> d.getCiudad() != null);
            estado = datos.filtered(d -> d.getEstado() != null);

            Utilidades.configurarComboBoxMostrarCampo(cbColoniaDestinatario, Direccion::getColonia, colonias);
            Utilidades.configurarComboBoxMostrarCampo(cbCiudadDestinatario, Direccion::getCiudad, ciudad);
            Utilidades.configurarComboBoxMostrarCampo(cbEstadoDestinatario, Direccion::getEstado, estado);
            
            return false;
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            return true;
        }
    }

    @FXML
    private void btnBuscarCodigoPostal(ActionEvent event) {
        
        if(!cargarColonias(Integer.parseInt(tfCodigoPostalDestinatario.getText()))){
            cbColoniaDestinatario.setDisable(false);
            cbCiudadDestinatario.setDisable(false);
            cbEstadoDestinatario.setDisable(false);
        }
        
    }

    @FXML
    private void btnCrear(ActionEvent event) {
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
    }
    
    /*private int obtenerPosicionCliente(int idCliente){
        //for int i=0;i<cl
    }*/
    
}
