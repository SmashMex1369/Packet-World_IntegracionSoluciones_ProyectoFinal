package packetworldclienteescritorio;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    
    private Conductor colaboradorEdicion;
    private INotificador observador;
    private ObservableList<CUS> sucursales;
    private ObservableList<Rol> roles;
    private File foto;
    private byte[] fotoBytes;

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
        cargarFotoActual();
        
    }
    
    private void cargarFotoActual() {
        if (colaboradorEdicion != null && colaboradorEdicion.getIdColaborador() != null) {
            HashMap<String, Object> respuesta = ColaboradorImp.obtenerFotoColaborador(
                colaboradorEdicion.getIdColaborador());
            
            if (!(boolean) respuesta.get(Constantes.KEY_ERROR)) {
                String stringBase64 = (String) respuesta.get(Constantes.KEY_FOTO);
                if (stringBase64 != null && stringBase64.length() > 0) {
                    Utilidades.colocarImagen(stringBase64, imgvFotoPerfil, getClass());
                }else{
                    Utilidades.cargarImagenPorDefecto(imgvFotoPerfil, getClass());
                }
            } else {
                Utilidades.cargarImagenPorDefecto(imgvFotoPerfil, getClass());
            }
        } else {
            Utilidades.cargarImagenPorDefecto(imgvFotoPerfil, getClass());
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
            colaborador.setNombre(tfNombre.getText().trim());
            colaborador.setApellidoPaterno(tfApellidoPaterno.getText().trim());
            colaborador.setApellidoMaterno(tfApellidoMaterno.getText().trim());
            colaborador.setCURP(tfCURP.getText().toUpperCase().trim());
            colaborador.setCorreo(tfCorreo.getText().trim());
            colaborador.setIdSucursal(cbSucursal
                    .getSelectionModel().getSelectedItem().getIdSucursal());
            if (tfContraseña.getText().trim().isEmpty()) {
                colaborador.setContraseña(null);
            }else{
                colaborador.setContraseña(tfContraseña.getText().trim());
            }
            actualizarColaborador(colaborador);
        }
    }
    
    // Método para validar CURP (18 caracteres, formato específico)
private boolean esCURPValido(String curp) {
    if (curp == null || curp.length() != 18) {
        return false;
    }
    
    // Expresión regular para CURP mexicano
    String regex = "^[A-Z]{4}[0-9]{6}[HM]{1}[A-Z]{5}[A-Z0-9]{1}[0-9]{1}$";
    return curp.toUpperCase().matches(regex);
}
// Método para validar email
private boolean esEmailValido(String email) {
    if (email == null || email.isEmpty()) {
        return false;
    }
    
    // Expresión regular para email básico
    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    return email.matches(regex);
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
        // Validar CURP
    String curp = tfCURP.getText();
    if(curp == null || curp.trim().isEmpty()){
        camposValidos = false;
        tfCURP.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
    } else if (!esCURPValido(curp.toUpperCase())) {
        camposValidos = false;
        tfCURP.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
    }
        if(tfCorreo.getText()==null || tfCorreo.getText().isEmpty()){
            camposValidos=false;
            tfCorreo.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        // Validar correo
    String correo = tfCorreo.getText();
    if(correo == null || correo.trim().isEmpty()){
        camposValidos = false;
        tfCorreo.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
    } else if (!esEmailValido(correo)) {
        camposValidos = false;
        tfCorreo.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
    }
        if(cbSucursal.getSelectionModel().getSelectedIndex()== -1){
            camposValidos=false;
            cbSucursal.setStyle("-fx-border-color: #bf0b0b; -fx-font-size: 21; -fx-border-insets: -1");
        }   
        if (tfContraseña.getText()!=null && !tfContraseña.getText().isEmpty()) {
            if(tfContraseña.getText().trim().length()<8||tfContraseña.getText().trim().length()>20){
                camposValidos = false;
                tfContraseña.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
            }
        }
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple(
            "Campos incorrectos", 
            "Hay datos faltantes o no tienen el formato adecuado:\n" +
            "• CURP debe tener 18 caracteres en formato válido\n" +
            "• Correo debe tener formato válido (ejemplo@dominio.com)\n" +
            "• Contraseña debe tener entre 8 a 20 caracteres (Opcional)", 
            Alert.AlertType.ERROR
        );
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
        FileChooser.ExtensionFilter filtroImg = new FileChooser.ExtensionFilter("Archivos de imagenes", "*.jpg", "*.png", ".jpeg", ".avif");
        dialogo.getExtensionFilters().add(filtroImg);
        foto =dialogo.showOpenDialog(imgvFotoPerfil.getScene().getWindow());
        if (foto != null) {
            try {
                byte[] fotoEnBytes = procesarImagenCuadrada(foto);
                InputStream is = new FileInputStream (foto);
                Image image = new Image(is, 0, 100, true, true);
                imgvFotoPerfil.setImage(image);
                Circle clip = new Circle(imgvFotoPerfil.getImage().getWidth()/2, 50, 50);
                imgvFotoPerfil.setClip(clip);
                boolean confirmarOperacion = Utilidades.mostrarAlertaConfirmacion("Actualizar foto (Vista previa)", "¿Estas seguro de actualizar la foto del colaborador con la seleccionada?");
                if(confirmarOperacion){
                    int idColaborador = colaboradorEdicion.getIdColaborador(); 
                    Respuesta respuesta = ColaboradorImp.subirFotoColaborador(idColaborador, fotoEnBytes);
                    if (!respuesta.isError()) {
                        Utilidades.mostrarAlertaSimple(
                            "Foto actualizada", 
                            respuesta.getMensaje(), 
                            Alert.AlertType.INFORMATION
                        );
                    } else {
                        Utilidades.mostrarAlertaSimple(
                            "Error", 
                            respuesta.getMensaje(), 
                            Alert.AlertType.ERROR
                        );
                        cargarFotoActual();
                    }
                }else{
                    cargarFotoActual();
                    seleccionarFoto();
                }
            } catch (IOException e) {
                Utilidades.mostrarAlertaSimple("Error", "Error al cargar la foto", Alert.AlertType.ERROR);
            }
        }
    }
    
    private byte[] procesarImagenCuadrada(File archivoFoto) throws IOException {
        BufferedImage imagenOriginal = ImageIO.read(archivoFoto);

        int ancho = imagenOriginal.getWidth();
        int alto = imagenOriginal.getHeight();
        int lado = Math.min(ancho, alto);

        int x = (ancho - lado) / 2;
        int y = (alto - lado) / 2;

        BufferedImage imagenRecortada = imagenOriginal.getSubimage(x, y, lado, lado);
        
        int tamañoObjetivo = 500;
        BufferedImage imagenFinal = new BufferedImage(tamañoObjetivo, tamañoObjetivo, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = imagenFinal.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(imagenRecortada, 0, 0, tamañoObjetivo, tamañoObjetivo, null);
        g2d.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String extension = archivoFoto.getName().substring(archivoFoto.getName().lastIndexOf(".") + 1);
        ImageIO.write(imagenFinal, extension, baos);

        return baos.toByteArray();
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
            Utilidades.remaximizar(escenario, escena);
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

    @FXML
    private void perderFoco(MouseEvent event) {
        tfNombre.getParent().requestFocus();
    }
    
}
