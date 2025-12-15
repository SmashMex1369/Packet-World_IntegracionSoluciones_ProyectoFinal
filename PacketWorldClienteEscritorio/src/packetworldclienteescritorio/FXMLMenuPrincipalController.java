package packetworldclienteescritorio;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import packetworldclienteescritorio.pojo.Colaborador;
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
            escenarioBase.setScene(escenaLogin);
            escenarioBase.setTitle("Login");
            escenarioBase.show();
        } catch (IOException ex) {
           Utilidades.mostrarAlertaSimple("Error", "Por el momento no se puede mostrar la pantalla principal", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clickCerrarSesion(javafx.scene.input.MouseEvent event) {
        //Utilidades.mostrarAlertaConfirmacion(titulo, contenido);
        Sesion.cerrarSesion();
        irPantallaLogin();
    }
    
    public void cargarInformacion(Colaborador colaborador){
        colaboradorSesion = colaborador;
        lbSaludo.setText(colaboradorSesion.getNombre() + " " + colaboradorSesion.getApellidoPaterno() + " " + colaboradorSesion.getApellidoMaterno());
        lbTipoRol.setText("Rol: " + colaboradorSesion.getRol());
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
