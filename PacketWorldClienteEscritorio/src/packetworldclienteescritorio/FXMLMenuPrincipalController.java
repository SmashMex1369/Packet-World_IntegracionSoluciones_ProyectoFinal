package packetworldclienteescritorio;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
            escenarioBase.setMaximized(false);
            escenarioBase.setScene(escenaLogin);
            escenarioBase.centerOnScreen();
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
        cargarFotoPerfil();
    }
    
    private void cargarFotoPerfil() {
        try {
            if (colaboradorSesion != null && colaboradorSesion.getIdColaborador() != null) {
                HashMap<String, Object> respuesta = ColaboradorImp.obtenerFotoColaborador(
                    colaboradorSesion.getIdColaborador());
                if (!(boolean) respuesta.get(Constantes.KEY_ERROR)) {
                    String stringBase64  = (String) respuesta.get(Constantes.KEY_FOTO);
                    colaboradorSesion.setFotoBase64(stringBase64);

                    if (stringBase64 != null && stringBase64.length() > 0) {
                        Utilidades.colocarImagen(stringBase64, ivFoto, getClass());
                    } else {
                        Utilidades.cargarImagenPorDefecto(ivFoto, getClass());
                    }
                } else {
                    Utilidades.cargarImagenPorDefecto(ivFoto, getClass());
                }
            } else {
                Utilidades.cargarImagenPorDefecto(ivFoto, getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilidades.cargarImagenPorDefecto(ivFoto, getClass());
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
        boolean confirmarOperacion=Utilidades.mostrarAlertaConfirmacion("Cerrar sesion", "¿Esta seguro que desea cerrar la sesion actual?\nDebera volver a iniciar sesion");
        if (confirmarOperacion) {
            Sesion.cerrarSesion();
            irPantallaLogin();
        }        
    }
    
    public void irAdmUnidades(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionUnidades.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) lbSaludo.getScene().getWindow();
            Utilidades.remaximizar(escenario, escena);
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
            Utilidades.remaximizar(escenario, escena);
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
            Utilidades.remaximizar(escenario, escena);
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
            Utilidades.remaximizar(escenario, escena);
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
            Utilidades.remaximizar(escenario, escena);
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
            Utilidades.remaximizar(escenario, escena);
            escenario.setTitle("Administracion Colaboradores");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
