package packetworldclienteescritorio;

import java.awt.Canvas;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.ColaboradorImp;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Sesion;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private Label lbSaludo;
    @FXML
    private ImageView ivFoto;
    @FXML
    private Label lbTipoRol;
    
    private Colaborador colaboradorSesion;
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private Circle circuloFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacion(Sesion.getColaborador());
        
    }
    
    private void irPantallaLogin(){
        try {
            Stage escenarioBase = (Stage) imgCerrarSesion.getScene().getWindow();
            Parent login = FXMLLoader.load(getClass().getResource("FXMLInicioSesion.fxml"));
            Scene escenaLogin = new Scene(login);
            escenarioBase.setScene(escenaLogin);
            escenarioBase.setTitle("Packet-World Login");
            escenarioBase.setResizable(false);
            escenarioBase.show();
        } catch (IOException ex) {
           Utilidades.mostrarAlertaSimple("Error", "Por el momento no se puede mostrar la pantalla principal", Alert.AlertType.ERROR);
        }
    }
    
    public void cargarInformacion(Colaborador colaborador){
        colaboradorSesion = colaborador;
        lbSaludo.setText("Bienvenido(a) "+ colaboradorSesion.getNombre() + " " + colaboradorSesion.getApellidoPaterno() + " " + colaboradorSesion.getApellidoMaterno());
        lbTipoRol.setText("Rol: " + colaboradorSesion.getRol());
        
        // nuevo
        cargarFotoPerfil();
    }
    
    // nuevo
    private void cargarFotoPerfil() {
    try {
        if (colaboradorSesion != null && colaboradorSesion.getIdColaborador() != null) {
            // Obtener foto del colaborador desde la API
            HashMap<String, Object> respuesta = ColaboradorImp.obtenerFotoColaborador(
                colaboradorSesion.getIdColaborador());
            
            if (!(boolean) respuesta.get(Constantes.KEY_ERROR)) {
                // La foto viene como byte[] directamente
                byte[] fotoBytes = (byte[]) respuesta.get(Constantes.KEY_FOTO);
                
                if (fotoBytes != null && fotoBytes.length > 0) {
                    // Crear imagen desde bytes
                    ByteArrayInputStream bis = new ByteArrayInputStream(fotoBytes);
                    Image image = new Image(bis);
                    ivFoto.setImage(image);
                    
                    // Aplicar clip circular
                    aplicarClipCircular(ivFoto);
                } else {
                    cargarImagenPorDefecto();
                }
            } else {
                cargarImagenPorDefecto();
            }
        } else {
            cargarImagenPorDefecto();
        }
    } catch (Exception e) {
        e.printStackTrace();
        cargarImagenPorDefecto();
    }
}
    
    // nuevo
    private void cargarImagenPorDefecto() {
        try {
            InputStream is = getClass().getResourceAsStream("/recursos/circle-user-solid.png");
            if (is != null) {
                Image image = new Image(is, 100, 100, true, true);
                ivFoto.setImage(image);
                aplicarClipCircular(ivFoto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // nuevo
    private void aplicarClipCircular(ImageView imageView) {
    try {
        if (imageView.getImage() == null) return;
        
        // Obtener dimensiones reales de la imagen
        double imageWidth = imageView.getImage().getWidth();
        double imageHeight = imageView.getImage().getHeight();
        double fitWidth = imageView.getFitWidth();
        double fitHeight = imageView.getFitHeight();
        
        // Calcular escala
        double scaleX = fitWidth / imageWidth;
        double scaleY = fitHeight / imageHeight;
        double scale = Math.max(scaleX, scaleY); // Para mantener relación de aspecto
        
        // Calcular dimensiones escaladas
        double scaledWidth = imageWidth * scale;
        double scaledHeight = imageHeight * scale;
        
        // Calcular centro del ImageView
        double centerX = fitWidth / 2;
        double centerY = fitHeight / 2;
        
        // Calcular radio (usar el menor entre ancho y alto)
        double radius = Math.min(fitWidth, fitHeight) / 2;
        
        // Aplicar clip
        Circle clip = new Circle(centerX, centerY, radius);
        imageView.setClip(clip);
        
        // Ajustar viewport si es necesario para centrar la imagen
        if (scaledWidth > fitWidth || scaledHeight > fitHeight) {
            // Calcular viewport para centrar la imagen
            double viewportX = (scaledWidth - fitWidth) / (2 * scale);
            double viewportY = (scaledHeight - fitHeight) / (2 * scale);
            double viewportWidth = fitWidth / scale;
            double viewportHeight = fitHeight / scale;
            
            imageView.setViewport(new Rectangle2D(viewportX, viewportY, viewportWidth, viewportHeight));
        } else {
            imageView.setViewport(null); // Restablecer viewport
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void btnEnvios(ActionEvent event) {
        irAdmEnvios();
    }

    @FXML
    private void btnPaquetes(ActionEvent event) {
        irAdmPaquetes();
    }

    @FXML
    private void btnClientes(ActionEvent event) {
        irAdminClientes();
    }

    @FXML
    private void btnColaboradores(ActionEvent event) {
        irAdmColaboradores();
    }

    @FXML
    private void btnUnidades(ActionEvent event) {
        irAdmUnidades();
    }

    @FXML
    private void btnSucursales(ActionEvent event) {
        irAdminSucursales();
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        //Utilidades.mostrarAlertaConfirmacion(titulo, contenido);
        Sesion.cerrarSesion();
        irPantallaLogin();
    }
    
    public void irAdmUnidades(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionUnidades.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) lbSaludo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Unidades");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void irAdminClientes(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionClientes.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) lbSaludo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Clientes");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void irAdminSucursales(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionSucursales.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) lbSaludo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Sucursales");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void irAdmPaquetes(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionPaquetes.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) lbSaludo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Paquetes");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void irAdmEnvios(){
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLAdministracionEnvios.fxml"));
            Parent vista = cargador.load();
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) lbSaludo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administracion Envios");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void irAdmColaboradores(){
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLAdministracionColaboradores.fxml"));
            Parent vista = cargador.load();
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) lbSaludo.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administracion Colaboradores");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
