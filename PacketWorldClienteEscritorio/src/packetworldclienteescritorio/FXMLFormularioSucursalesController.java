/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.SucursalImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Direccion;
import packetworldclienteescritorio.pojo.Sucursal;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author OmarVX
 */
public class FXMLFormularioSucursalesController implements Initializable {

    @FXML
    private ComboBox<Direccion> cbColonia;
    @FXML
    private ComboBox<Direccion> cbCiudad;
    @FXML
    private ComboBox<Direccion> cbEstado;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumeroExterior;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfCodigoUnico;
    @FXML
    private TextField tfNombreCorto;
    
    private Sucursal sucursalEdicion;
    private INotificador observador;
    private ObservableList <Sucursal> sucursal;
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
        // TODO
        tfCodigoPostalFocusListener();
    }    
    
    public void inicializarDatosSucursales(Sucursal sucursalEdicion, INotificador observador){
        this.sucursalEdicion = sucursalEdicion;
        this.observador = observador;
        if(sucursalEdicion != null){
            lblTitulo.setText("Editar Sucursal");
            btnGuardar.setText("Actualizar");
            tfCodigoUnico.setText(sucursalEdicion.getCUS());
            tfCodigoUnico.setDisable(true);
            tfNombreCorto.setText(sucursalEdicion.getNombre());
            tfCodigoPostal.setText(String.valueOf(sucursalEdicion.getCodigoPostal()));
            buscarCodigoPostal();
            cbColonia.getSelectionModel().select(obtenerPosicionColonia(sucursalEdicion.getIdColonia()));
            tfCalle.setText(sucursalEdicion.getCalle());
            tfNumeroExterior.setText(String.valueOf(sucursalEdicion.getNumero()));
        }else{
            lblTitulo.setText("Registrar Sucursal");
            btnGuardar.setText("Guardar");
        }
    }
    
    //VALIDACIONES de campos faltantes
    private boolean sonCamposValidos(){
        boolean camposValidos = true;
        if(tfCodigoUnico.getText()==null || tfCodigoUnico.getText().isEmpty()){
            camposValidos = false;
            tfCodigoUnico.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfNombreCorto.getText()==null || tfNombreCorto.getText().isEmpty()){
            camposValidos = false;
            tfNombreCorto.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfNombreCorto.getText()==null || tfNombreCorto.getText().isEmpty()){
            camposValidos = false;
            tfNombreCorto.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
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
    
    private int obtenerPosicionColonia(int idColonia){
        for (int i=0;i<colonias.size();i++){
            if (colonias.get(i).getIdColonia()==idColonia) {
                return i;
            }
        }
        return -1;
    }

    @FXML
    private void clicBtnGuardar(ActionEvent event) {
        if(sonCamposValidos()){
            Sucursal sucursal = new Sucursal();
            sucursal.setCUS(tfCodigoUnico.getText());
            sucursal.setNombre(tfNombreCorto.getText());
            sucursal.setCalle(tfCalle.getText());
            sucursal.setNumero(Integer.parseInt(tfNumeroExterior.getText()));
            sucursal.setCodigoPostal(Integer.parseInt(tfCodigoPostal.getText()));
            Direccion idColoniaSeleccionado = cbColonia.getSelectionModel().getSelectedItem();
            sucursal.setIdColonia(idColoniaSeleccionado.getIdColonia());
            if(sucursalEdicion==null){
                registrarSucursal(sucursal);
            }else{
                editarSucursal(sucursal);             
            }
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }
    
    private void cargarSucursales(){
        HashMap<String, Object> respuesta= SucursalImp.obtenerTodos();
        if (!(boolean) respuesta.get(Constantes.KEY_ERROR)){
            List<Sucursal> sucursalAPI= (List<Sucursal>) respuesta.get(Constantes.KEY_LISTA);
            sucursal = FXCollections.observableArrayList();
            sucursal.addAll(sucursalAPI);
            cbCiudad.setItems(ciudades);
        }else{
            Utilidades.mostrarAlertaSimple("Error", respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            regresarVentana();
        }
    }
    
    private void registrarSucursal(Sucursal sucursal){
        Respuesta respuestaCUS= SucursalImp.verificarCUS(sucursal.getCUS());
        if(!respuestaCUS.isError()){
            Respuesta respuesta = SucursalImp.registrarSucursal(sucursal);
            if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Sucursal registrada con éxito", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("registro", sucursal.getNombre());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        }else{
            Utilidades.mostrarAlertaSimple("CUS ocupado", respuestaCUS.getMensaje(), Alert.AlertType.WARNING);   
        }
    }
    
    private void editarSucursal(Sucursal sucursal){
        sucursal.setIdSucursal(sucursalEdicion.getIdSucursal());
        Respuesta respuesta= SucursalImp.editarSucursal(sucursal);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Sucursal actualizada", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("edición", sucursal.getNombre());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionSucursales.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfCodigoUnico.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Sucursales");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBuscarCodigoPostal(ActionEvent event) {
        buscarCodigoPostal();
    }

    @FXML
    private void tfCodigoPostalEnter(ActionEvent event) {
        buscarCodigoPostal();
        lblTitulo.getParent().requestFocus();
    }
    
}
