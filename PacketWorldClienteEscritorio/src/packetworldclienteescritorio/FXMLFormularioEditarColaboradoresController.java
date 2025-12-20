package packetworldclienteescritorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.ColaboradorImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.CUS;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.pojo.Conductor;
import packetworldclienteescritorio.pojo.Rol;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLFormularioEditarColaboradoresController implements Initializable {

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
    @FXML
    private ImageView imgvFotoPerfil;
    @FXML
    private Circle circulo;
    
    private Conductor colaboradorEdicion;
    private INotificador observador;
    private ObservableList<CUS> sucursales;
    private ObservableList<Rol> roles;
    private File foto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRoles();
        cargarSucursalesDisponibles();
    }    
    
    public void inicializarDatos(Conductor colaboradorEdicion, INotificador observador){
        this.colaboradorEdicion = colaboradorEdicion;
        this.observador = observador;
        tfNombre.setText(colaboradorEdicion.getNombre());
        tfApellidoPaterno.setText(colaboradorEdicion.getApellidoPaterno());
        tfApellidoMaterno.setText(colaboradorEdicion.getApellidoMaterno());
        tfCURP.setText(colaboradorEdicion.getCURP());
        tfCorreo.setText(colaboradorEdicion.getCorreo());
        cbSucursal.getSelectionModel().select(
                obtenerPosicionSucursal(colaboradorEdicion.getIdSucursal()));
        tfNoPersonal.setText(colaboradorEdicion.getNoPersonal());
        cbRol.getSelectionModel().select(
                obtenerPosicionRol(colaboradorEdicion.getIdRol()));
        if (colaboradorEdicion.getIdRol()==3) {
            tfNoLicencia.setText(colaboradorEdicion.getNoLicencia());
        }else{
            tfNoLicencia.setText(null);
        }
        
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

    @FXML
    private void btnActualizar(ActionEvent event) {
        if (sonCamposValidos()) {
            Colaborador colaborador = new Colaborador();
            colaborador.setIdColaborador(colaboradorEdicion.getIdColaborador());
            colaborador.setNombre(tfNombre.getText());
            colaborador.setApellidoPaterno(tfApellidoPaterno.getText());
            colaborador.setApellidoMaterno(tfApellidoMaterno.getText());
            colaborador.setCURP(tfCURP.getText());
            colaborador.setCorreo(tfCorreo.getText());
            colaborador.setIdSucursal(cbSucursal
                    .getSelectionModel().getSelectedItem().getIdSucursal());
            if (tfContraseña.getText().isEmpty()) {
                colaborador.setContraseña(null);
            }else{
                colaborador.setContraseña(tfContraseña.getText());
            }
            actualizarColaborador(colaborador);
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
        if(cbSucursal.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbSucursal.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }        
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes o no tienen el formato adecuado.", Alert.AlertType.ERROR);
        }
        return camposValidos;
    }

    private void actualizarColaborador(Colaborador colaborador){
        Respuesta respuesta = ColaboradorImp.actualizarColaborador(colaborador);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Colaborador actualizado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Actualizar colaborador", colaboradorEdicion.getNoPersonal());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al actualizar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnSeleccionarImagen(ActionEvent event) {
        seleccionarFoto();
    }
    
    private void seleccionarFoto(){
        FileChooser dialogo = new FileChooser();
        dialogo.setTitle("Selecciona una foto");
        FileChooser.ExtensionFilter filtroImg = new FileChooser.ExtensionFilter("Archivos de imagenes", "*.jpg", "*.png");
        dialogo.getExtensionFilters().add(filtroImg);
        foto =dialogo.showOpenDialog(imgvFotoPerfil.getScene().getWindow());
        if (foto != null) {
            try {
                InputStream is = new FileInputStream (foto);
                Image image = new Image(is, 0, 108, true, true);
                imgvFotoPerfil.setImage(image);
                Circle clip = new Circle(imgvFotoPerfil.getImage().getWidth()/2, imgvFotoPerfil.getImage().getHeight()/2, 54);
                imgvFotoPerfil.setClip(clip);
                boolean confirmarOperacion = Utilidades.mostrarAlertaConfirmacion("Actualizar foto (Vista previa)", "¿Estas seguro de actualizar la foto del colaborador con la seleccionada?");
                if(confirmarOperacion){
                    //LLamado al metodo de guardar foto
                }else{
                    is = getClass().getResourceAsStream("/recursos/circle-user-solid.png");
                    image = new Image(is, 108, 108, true, true);
                    imgvFotoPerfil.setImage(image); //Cambiar a metodo de obtener foto colaborador
                    imgvFotoPerfil.setClip(null);
                    seleccionarFoto();
                }
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Error", "Error al cargar la foto", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }
    
    private int obtenerPosicionSucursal(int idSucural){
        for(int i = 0; i<sucursales.size(); i++){
            if(sucursales.get(i).getIdSucursal()==idSucural){
                return i;
            }
        }
        return -1;
    }
    
    private int obtenerPosicionRol(int idRol){
        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).getIdRol()==idRol){
                return i;
            }
        }
        return -1;
    }
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionColaboradores.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfNombre.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administracion Colaboradores");             
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
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
    private void cbSucursalSeleccion(ActionEvent event) {
        cbSucursal.setStyle("-fx-font-size: 21");
    }
    
}
