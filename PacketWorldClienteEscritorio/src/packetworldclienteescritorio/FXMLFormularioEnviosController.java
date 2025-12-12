package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
        tfCodigoPostalFocusListener();
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
    
    private void cargarColonias(int codigoPostal){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerColonias(codigoPostal);
        boolean esError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!esError) {
            List<Direccion> coloniasAPI = (List<Direccion>) respuesta.get(Constantes.KEY_LISTA);
            if(!coloniasAPI.isEmpty()){
                ObservableList<Direccion> datos = FXCollections.observableArrayList(coloniasAPI);

                colonias = datos.filtered(d -> d.getColonia() != null);
                ciudad = datos.filtered(d -> d.getCiudad() != null);
                estado = datos.filtered(d -> d.getEstado() != null);

                Utilidades.configurarComboBoxMostrarCampo(cbColoniaDestinatario, Direccion::getColonia, colonias);
                Utilidades.configurarComboBoxMostrarCampo(cbCiudadDestinatario, Direccion::getCiudad, ciudad);
                Utilidades.configurarComboBoxMostrarCampo(cbEstadoDestinatario, Direccion::getEstado, estado);

                cbCiudadDestinatario.getSelectionModel().select(0);
                cbCiudadDestinatario.setStyle("-fx-font-size: 21");
                cbEstadoDestinatario.getSelectionModel().select(0);
                cbEstadoDestinatario.setStyle("-fx-font-size: 21");
                if(cbColoniaDestinatario.getItems().size()==1){
                    cbColoniaDestinatario.getSelectionModel().select(0);
                    cbColoniaDestinatario.setStyle("-fx-font-size: 21");
                }
                cbColoniaDestinatario.setDisable(false);
                cbCiudadDestinatario.setDisable(false);
                cbEstadoDestinatario.setDisable(false);
            }else{
                cbColoniaDestinatario.setDisable(true);
                cbColoniaDestinatario.setItems(null);
                cbCiudadDestinatario.setDisable(true);
                cbCiudadDestinatario.setItems(null);
                cbEstadoDestinatario.setDisable(true);
                cbEstadoDestinatario.setItems(null);
                Utilidades.mostrarAlertaSimple("Codigo Postal incorrecto", "El codigo postal a buscar no existe, favor de verificarlo.", Alert.AlertType.INFORMATION);
            }
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnBuscarCodigoPostal(ActionEvent event) {
        buscarCodigoPostal();
    }

    @FXML
    private void btnCrear(ActionEvent event) {
        if (sonCamposValidos()) {
            Envio envio = new Envio();
            String noGuia = generarNoGuia();
            envio.setNoGuia(noGuia);
            envio.setNombreDest(tfNombreDestinatario.getText());
            envio.setApellidoPatDest(tfApellidoPaternoDestinatario.getText());
            envio.setApellidoMatDest(tfApellidoMaternoDestinatario.getText());
            envio.setNumDest(Integer.parseInt(tfNumeroDestinatario.getText()));
            envio.setCalleDest(tfCalleDestinatario.getText());
            //envio.setIdCliente(Integer.SIZE);
            //envio.setIdSucursal(Integer.SIZE);
            Direccion idColoniaSeleccionado = cbColoniaDestinatario.getSelectionModel().getSelectedItem();
            envio.setIdColoniaDest(idColoniaSeleccionado.getIdColonia());
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }
    
    private boolean sonCamposValidos(){
        boolean camposValidos = true;
        if(cbCliente.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbCliente.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(tfNombreDestinatario.getText()==null || tfNombreDestinatario.getText().isEmpty()){
            camposValidos = false;
            tfNombreDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfApellidoPaternoDestinatario.getText()==null || tfApellidoPaternoDestinatario.getText().isEmpty()){
            camposValidos = false;
            tfApellidoPaternoDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfApellidoMaternoDestinatario.getText()==null || tfApellidoMaternoDestinatario.getText().isEmpty()){
            camposValidos = false;
            tfApellidoMaternoDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfApellidoMaternoDestinatario.getText()==null || tfApellidoMaternoDestinatario.getText().isEmpty()){
            camposValidos = false;
            tfApellidoMaternoDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfCodigoPostalDestinatario.getText()!=null || !tfCodigoPostalDestinatario.getText().isEmpty()){
            try {
                if(Integer.parseInt(tfCodigoPostalDestinatario.getText())<1000 || Integer.parseInt(tfCodigoPostalDestinatario.getText())>99999 ){
                    camposValidos=false;
                    tfCodigoPostalDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
                }
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfCodigoPostalDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
            }
        }else{
            camposValidos=false;
            tfCodigoPostalDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
        }
        if(cbEstadoDestinatario.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbEstadoDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(cbCiudadDestinatario.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbCiudadDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(cbColoniaDestinatario.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbColoniaDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(tfCalleDestinatario.getText()==null || tfCalleDestinatario.getText().isEmpty()){
            camposValidos = false;
            tfCalleDestinatario.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if (tfNumeroDestinatario.getText()!=null || !tfNumeroDestinatario.getText().isEmpty()){
            try {
                if(Integer.parseInt(tfNumeroDestinatario.getText())<=0){
                    camposValidos=false;
                    tfNumeroDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
                }
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfNumeroDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
            }
        }else{
            camposValidos=false;
            tfNumeroDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
        }
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes o no tienen el formato adecuado.", Alert.AlertType.ERROR);
        }
        return camposValidos;
    }
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador;
            String titulo;
            if (envioEdicion==null) {
                cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionEnvios.fxml"));
                titulo = "Administracion Envios";
            }else{
                 cargador= new FXMLLoader(getClass().getResource("FXMLDetallesEnviosEnvios.fxml"));
                titulo = "Detalles Envios";
            }
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) cbColoniaDestinatario.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle(titulo);
            escenario.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private String generarNoGuia() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("PW");

        for (int i = 0; i < 6; i++) {
            char letra = (char) ('A' + random.nextInt(26));
            sb.append(letra);
        }

        for (int i = 0; i < 6; i++) {
            int numero = random.nextInt(10);
            sb.append(numero);
        }

        return sb.toString();
    }
    
    private void buscarCodigoPostal(){
        try {
            int codigoPostal = Integer.parseInt(tfCodigoPostalDestinatario.getText());
            if(codigoPostal>999 && codigoPostal<100000){
                cargarColonias(codigoPostal);
            }else{
                cbColoniaDestinatario.setDisable(true);
                cbColoniaDestinatario.setItems(null);
                cbCiudadDestinatario.setDisable(true);
                cbCiudadDestinatario.setItems(null);
                cbEstadoDestinatario.setDisable(true);
                cbEstadoDestinatario.setItems(null);
                tfCodigoPostalDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
                Utilidades.mostrarAlertaSimple("Codigo Postal incorrecto", "El codigo postal a buscar no es valido, favor de ingresar un valor entre 1000 y 99999.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            cbColoniaDestinatario.setDisable(true);
            cbColoniaDestinatario.setItems(null);
            cbCiudadDestinatario.setDisable(true);
            cbCiudadDestinatario.setItems(null);
            cbEstadoDestinatario.setDisable(true);
            cbEstadoDestinatario.setItems(null);
            tfCodigoPostalDestinatario.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
            Utilidades.mostrarAlertaSimple("Formato incorrecto", "El valor ingresado no es valido, favor de verificarlo.", Alert.AlertType.ERROR);
        }
    }
    
    private void tfCodigoPostalFocusListener(){
        tfCodigoPostalDestinatario.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tfCodigoPostalDestinatario.setText("");
                cbColoniaDestinatario.setDisable(true);
                cbColoniaDestinatario.setItems(null);
                cbCiudadDestinatario.setDisable(true);
                cbCiudadDestinatario.setItems(null);
                cbEstadoDestinatario.setDisable(true);
                cbEstadoDestinatario.setItems(null);
            } 
        });
    }
    
    /*private int obtenerPosicionCliente(int idCliente){
        //for int i=0;i<cl
    }*/

    @FXML
    private void cbClienteSeleccion(Event event) {
        cbCliente.setStyle("-fx-font-size: 21");
    }

    @FXML
    private void tfNombreDestinatarioTexto(KeyEvent event) {
        tfNombreDestinatario.setStyle(null);
    }

    @FXML
    private void tfApellidoPaternoDestinatarioTexto(KeyEvent event) {
        tfApellidoPaternoDestinatario.setStyle(null);
    }

    @FXML
    private void tfApellidoMaternoDestinatarioTexto(KeyEvent event) {
        tfApellidoMaternoDestinatario.setStyle(null);
    }

    @FXML
    private void tfCodigoPostalDestinatarioTexto(KeyEvent event) {
        tfCodigoPostalDestinatario.setStyle(null);
    }

    @FXML
    private void cbEstadoDestinatarioSeleccion(Event event) {
        cbEstadoDestinatario.setStyle("-fx-font-size: 21");
    }

    @FXML
    private void cbCiudadDestinatarioSeleccion(Event event) {
        cbCiudadDestinatario.setStyle("-fx-font-size: 21");
    }

    @FXML
    private void cbColoniaDestinatarioSeleccion(Event event) {
        cbColoniaDestinatario.setStyle("-fx-font-size: 21");
    }

    @FXML
    private void tfCalleDestinatarioTexto(KeyEvent event) {
        tfCalleDestinatario.setStyle(null);
    }

    @FXML
    private void tfNumeroDestinatarioTexto(KeyEvent event) {
        tfNumeroDestinatario.setStyle(null);
    }

    @FXML
    private void perderFoco(MouseEvent event) {
        lbTitulo.getParent().requestFocus();
    }

    @FXML
    private void tfCodigoPostalEnter(ActionEvent event) {
        buscarCodigoPostal();
    }
    
    
    
}
