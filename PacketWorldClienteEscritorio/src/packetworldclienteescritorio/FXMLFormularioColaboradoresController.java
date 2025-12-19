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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.ColaboradorImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.CUS;
import packetworldclienteescritorio.pojo.Conductor;
import packetworldclienteescritorio.pojo.Rol;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLFormularioColaboradoresController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCURP;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private ComboBox<CUS> cbSucursal;
    @FXML
    private ComboBox<Rol> cbRol;
    @FXML
    private TextField tfContraseña;
    @FXML
    private TextField tfNoLicencia;
    
    private INotificador observador;
    private ObservableList<Rol> roles;
    private ObservableList<CUS> sucursales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRoles();
        cargarSucursalesDisponibles();
    }    
    
    public void observador(INotificador observador){
        this.observador = observador;
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
        if (sonCamposValidos()){
            Conductor colaborador = new Conductor();
            colaborador.setNombre(tfNombre.getText());
            colaborador.setApellidoPaterno(tfApellidoPaterno.getText());
            colaborador.setApellidoMaterno(tfApellidoMaterno.getText());
            colaborador.setCURP(tfCURP.getText());
            colaborador.setCorreo(tfCorreo.getText());
            colaborador.setNoPersonal(tfNoPersonal.getText());
            colaborador.setIdSucursal(cbSucursal
                    .getSelectionModel().getSelectedItem().getIdSucursal());
            colaborador.setIdRol(cbRol
                    .getSelectionModel().getSelectedItem().getIdRol());
            colaborador.setContraseña(tfContraseña.getText());
            colaborador.setNoLicencia(tfNoLicencia.getText());
            registrar(colaborador);
        }
    }
    
    private void registrar(Conductor colaborador){
        Respuesta respuesta = ColaboradorImp.registrarColaborador(colaborador);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Colaborador resgitrado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Crear colaborador", colaborador.getNoPersonal());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private boolean sonCamposValidos(){
        boolean camposValidos = true;
        if(tfNombre.getText()==null || tfNombre.getText().isEmpty()){
            camposValidos=false;
            tfNombre.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfApellidoPaterno.getText()==null || tfApellidoPaterno.getText().isEmpty()){
            camposValidos=false;
            tfApellidoPaterno.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfCURP.getText()==null || tfCURP.getText().isEmpty()){
            camposValidos=false;
            tfCURP.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfCorreo.getText()==null || tfCorreo.getText().isEmpty()){
            camposValidos=false;
            tfCorreo.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfNoPersonal.getText()==null || tfNoPersonal.getText().isEmpty()){
            camposValidos=false;
            tfNoPersonal.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(cbSucursal.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbSucursal.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }
        if(cbRol.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbRol.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }else{
            if(cbRol.getSelectionModel().getSelectedItem().getIdRol()==3&&
                (tfNoLicencia.getText()==null || tfNoLicencia.getText().isEmpty())){
                camposValidos=false;
                tfNoLicencia.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
            }
        }
        if(tfContraseña.getText()==null || tfContraseña.getText().isEmpty()){
            camposValidos=false;
            tfContraseña.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes o no tienen el formato adecuado.", Alert.AlertType.ERROR);
        }
        return camposValidos;
    }
    
    private void cargarSucursalesDisponibles(){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerSucursalesDisponibles();
        if (!(boolean) respuesta.get(Constantes.KEY_ERROR)) {
            List<CUS> sucursalAPI = (List<CUS>) respuesta.get(Constantes.KEY_LISTA);
            sucursales = FXCollections.observableArrayList();
            sucursales.addAll(sucursalAPI);
            cbSucursal.setItems(sucursales);
        }else{
            Utilidades.mostrarAlertaSimple("Error", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
            regresarVentana();
        }
    }
    
    private void cargarRoles(){
        HashMap<String, Object> respuesta = CatalogoImp.obtenerRoles();
        if (!(boolean) respuesta.get(Constantes.KEY_ERROR)) {
            List<Rol> rolesAPI = (List<Rol>) respuesta.get(Constantes.KEY_LISTA);
            roles = FXCollections.observableArrayList();
            roles.addAll(rolesAPI);
            cbRol.setItems(roles);
        }else{
            Utilidades.mostrarAlertaSimple("Error", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
            regresarVentana();
        }
    }
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionColaboradores.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfNombre.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Colaboradores");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cbRolSeleccion(ActionEvent event) {
        cbRol.setStyle("-fx-font-size: 21");
        if (cbRol.getSelectionModel().getSelectedItem().getIdRol()==3) {
            tfNoLicencia.setDisable(false);
        }else{
            tfNoLicencia.setStyle(null);
            tfNoLicencia.setText(null);
            tfNoLicencia.setDisable(true);
        }
    }

    @FXML
    private void tfNombreTexto(KeyEvent event) {
        tfNombre.setStyle(null);
    }

    @FXML
    private void tfApellidoPaternoTexto(KeyEvent event) {
        tfApellidoPaterno.setStyle(null);
    }

    @FXML
    private void tfCURPTexto(KeyEvent event) {
        tfCURP.setStyle(null);
    }

    @FXML
    private void tfCorreoTexto(KeyEvent event) {
        tfCorreo.setStyle(null);
    }

    @FXML
    private void tfNoPersonalTexto(KeyEvent event) {
        tfNoPersonal.setStyle(null);
    }

    @FXML
    private void cbSucursalSeleccion(ActionEvent event) {
        cbSucursal.setStyle("-fx-font-size: 21");
    }

    @FXML
    private void tfContraseñaTexto(KeyEvent event) {
        tfContraseña.setStyle(null);
    }

    @FXML
    private void tfNoLicenciaTexto(KeyEvent event) {
        tfNoLicencia.setStyle(null);
    }
    
}
