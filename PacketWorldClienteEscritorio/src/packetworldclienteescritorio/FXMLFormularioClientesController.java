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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.ClienteImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Cliente;
import packetworldclienteescritorio.pojo.Direccion;
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
    private ComboBox<Direccion> cbEstado;
    @FXML
    private ComboBox<Direccion> cbCiudad;
    @FXML
    private ComboBox<Direccion> cbColonia;
    
    private Cliente clienteEdicion;
    private INotificador observador;
    private ObservableList <Cliente> cliente;
    private ObservableList<Direccion> colonias;
    private ObservableList<Direccion> ciudades;
    private ObservableList<Direccion> estados;
    @FXML
    private Label lblTitulo;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      tfCodigoPostalFocusListener();
      
        soloNumeros(tfTelefono, 10);       // Teléfono 10 dígitos
        soloNumeros(tfCodigoPostal, 5);    // Código postal
        soloNumeros(tfNumeroExterior, 6);  // Número exterior
    }    
    
    public void inicializarDatosClientes(Cliente clienteEdicion, INotificador observador){
        this.clienteEdicion = clienteEdicion;
        this.observador = observador;
        if(clienteEdicion != null){
            lblTitulo.setText("Editar Cliente");
            btnGuardar.setText("Actualizar");
            tfNombre.setText(clienteEdicion.getNombre());
            tfApellidoPaterno.setText(clienteEdicion.getApellidoPaterno());
            tfApellidoMaterno.setText(clienteEdicion.getApellidoMaterno());
            tfTelefono.setText(clienteEdicion.getTelefono());
            tfCorreo.setText(clienteEdicion.getCorreo());
            tfCodigoPostal.setText(String.valueOf(clienteEdicion.getCodigoPostal()));
            tfCalle.setText(clienteEdicion.getCalle());
            tfNumeroExterior.setText(String.valueOf(clienteEdicion.getNumero()));
        }else{
            lblTitulo.setText("Registrar Cliente");
            btnGuardar.setText("Guardar");
        }
    }
    
        // VALIDACIONES DE CORREO Y NUMEROS (DIRECCION Y TELEFONICO)
    private void soloNumeros(TextField textField, int longitudMaxima) {
    textField.setTextFormatter(new TextFormatter<>(change -> {
        if (change.getControlNewText().matches("\\d{0," + longitudMaxima + "}")) {
            return change;
        }
        Utilidades.mostrarAlertaSimple(
                "Formato incorrecto",
                "Este campo solo acepta números.",
                Alert.AlertType.WARNING
        );
        return null;
    }));
}
    private static final String REGEX_CORREO =
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private boolean validarCorreo() {
    String correo = tfCorreo.getText();

    if (correo == null || correo.isEmpty() || correo.length() > 254) {
        tfCorreo.setStyle("-fx-border-color: #bf0b0b;");
        return false;
    }

    if (!correo.matches(REGEX_CORREO)) {
        tfCorreo.setStyle("-fx-border-color: #bf0b0b;");
        return false;
    }

    tfCorreo.setStyle(null);
    return true;
}
    
    private boolean sonCamposValidos(){
        boolean camposValidos = true;
        if(tfNombre.getText()==null || tfNombre.getText().isEmpty()){
            camposValidos = false;
            tfNombre.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfApellidoPaterno.getText()==null || tfApellidoPaterno.getText().isEmpty()){
            camposValidos = false;
            tfApellidoPaterno.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfApellidoMaterno.getText()==null || tfApellidoMaterno.getText().isEmpty()){
            camposValidos = false;
            tfApellidoMaterno.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfApellidoMaterno.getText()==null || tfApellidoMaterno.getText().isEmpty()){
            camposValidos = false;
            tfApellidoMaterno.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfCorreo.getText()==null || tfCorreo.getText().isEmpty()){
            camposValidos = false;
            tfCorreo.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if (!validarCorreo()) {
        camposValidos = false;
    }
        if(tfTelefono.getText()==null || tfTelefono.getText().isEmpty()){
            camposValidos = false;
            tfTelefono.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfCodigoPostal.getText()!=null || !tfCodigoPostal.getText().isEmpty()){
            try {
                if(Integer.parseInt(tfCodigoPostal.getText())<1000 || Integer.parseInt(tfCodigoPostal.getText())>99999 ){
                    camposValidos=false;
                    tfCodigoPostal.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
                }
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfCodigoPostal.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
            }
        }else{
            camposValidos=false;
            tfCodigoPostal.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
        }
        if(cbEstado.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbEstado.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(cbCiudad.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbCiudad.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(cbColonia.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbColonia.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(tfCalle.getText()==null || tfCalle.getText().isEmpty()){
            camposValidos = false;
            tfCalle.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if (tfNumeroExterior.getText()!=null || !tfNumeroExterior.getText().isEmpty()){
            try {
                if(Integer.parseInt(tfNumeroExterior.getText())<=0){
                    camposValidos=false;
                    tfNumeroExterior.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
                }
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfNumeroExterior.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
            }
        }else{
            camposValidos=false;
            tfNumeroExterior.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
        }
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes o no tienen el formato adecuado.", Alert.AlertType.ERROR);
        }
        return camposValidos;
    }
    
    private void cargarColonias(int codigoPostal){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerColonias(codigoPostal);
        boolean esError = (boolean) respuesta.get(Constantes.KEY_ERROR);
        if (!esError) {
            List<Direccion> coloniasAPI = (List<Direccion>) respuesta.get(Constantes.KEY_LISTA);
            if(!coloniasAPI.isEmpty()){
                ObservableList<Direccion> datos = FXCollections.observableArrayList(coloniasAPI);

                colonias = datos.filtered(d -> d.getColonia() != null);
                ciudades = datos.filtered(d -> d.getCiudad() != null);
                estados = datos.filtered(d -> d.getEstado() != null);

                Utilidades.configurarComboBoxMostrarCampo(cbColonia, Direccion::getColonia, colonias);
                Utilidades.configurarComboBoxMostrarCampo(cbCiudad, Direccion::getCiudad, ciudades);
                Utilidades.configurarComboBoxMostrarCampo(cbEstado, Direccion::getEstado, estados);

                cbCiudad.getSelectionModel().select(0);
                cbCiudad.setStyle("-fx-font-size: 21");
                cbEstado.getSelectionModel().select(0);
                cbEstado.setStyle("-fx-font-size: 21");
                if(cbColonia.getItems().size()==1){
                    cbColonia.getSelectionModel().select(0);
                    cbColonia.setStyle("-fx-font-size: 21");
                }
                cbColonia.setDisable(false);
                cbCiudad.setDisable(false);
                cbEstado.setDisable(false);
            }else{
                cbColonia.setDisable(true);
                cbColonia.setItems(null);
                cbCiudad.setDisable(true);
                cbCiudad.setItems(null);
                cbEstado.setDisable(true);
                cbEstado.setItems(null);
                Utilidades.mostrarAlertaSimple("Codigo Postal incorrecto", "El codigo postal a buscar no existe, favor de verificarlo.", Alert.AlertType.INFORMATION);
            }
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
        }
    }
    
    private void buscarCodigoPostal(){
        try {
            int codigoPostal = Integer.parseInt(tfCodigoPostal.getText());
            if(codigoPostal>999 && codigoPostal<100000){
                cargarColonias(codigoPostal);
            }else{
                cbColonia.setDisable(true);
                cbColonia.setItems(null);
                cbCiudad.setDisable(true);
                cbCiudad.setItems(null);
                cbEstado.setDisable(true);
                cbEstado.setItems(null);
                tfCodigoPostal.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
                Utilidades.mostrarAlertaSimple("Codigo Postal incorrecto", "El codigo postal a buscar no es valido, favor de ingresar un valor entre 1000 y 99999.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            cbColonia.setDisable(true);
            cbColonia.setItems(null);
            cbCiudad.setDisable(true);
            cbCiudad.setItems(null);
            cbEstado.setDisable(true);
            cbEstado.setItems(null);
            tfCodigoPostal.setStyle("-fx-border-color: #ff0000; -fx-border-insets: -1");
            Utilidades.mostrarAlertaSimple("Formato incorrecto", "El valor ingresado no es valido, favor de verificarlo.", Alert.AlertType.ERROR);
        }
    }
    
    private void tfCodigoPostalFocusListener(){
        tfCodigoPostal.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tfCodigoPostal.setText("");
                cbColonia.setDisable(true);
                cbColonia.setItems(null);
                cbCiudad.setDisable(true);
                cbCiudad.setItems(null);
                cbEstado.setDisable(true);
                cbEstado.setItems(null);
            } 
        });
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
            Direccion idColoniaSeleccionado = cbColonia.getSelectionModel().getSelectedItem();
            cliente.setIdColonia(idColoniaSeleccionado.getIdColonia());
            if(clienteEdicion==null){
                registrarCliente(cliente);
            }else{
                editarCliente(cliente);             
            }
        }
    }

    @FXML
    private void clicBtnCancelar(ActionEvent event) {
        regresarVentana();
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
            cbCiudad.setItems(ciudades);
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

    @FXML
    private void btnBuscarCodigoPostal(ActionEvent event) {
        buscarCodigoPostal();
    }
}
