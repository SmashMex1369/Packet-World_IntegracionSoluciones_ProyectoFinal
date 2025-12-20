package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.EnvioImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Envio;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLDetallesEnvioController implements Initializable, INotificador{

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEnvio;
    @FXML
    private SVGPath imgvEnvio;
    @FXML
    private Button btnRemitente;
    @FXML
    private SVGPath imgvRemitente;
    @FXML
    private Button btnDestinatario;
    @FXML
    private SVGPath imgvDestinatario;
    @FXML
    private Button btnSucursal;
    @FXML
    private SVGPath imgvSucursal;
    @FXML
    private GridPane grdpEnvio;
    @FXML
    private Label lbNoGuia;
    @FXML
    private Label lbNombreConductor;
    @FXML
    private Label lbNoLicencia;
    @FXML
    private Label lbEstatus;
    @FXML
    private Label lbTiempo;
    @FXML
    private Label lbNombreColaborador;
    @FXML
    private Label lbMotivo;
    @FXML
    private GridPane grdpRemitente;
    @FXML
    private Label lbNombreCliente;
    @FXML
    private Label lbTelefonoCliente;
    @FXML
    private Label lbCorreoCliente;
    @FXML
    private Label lbColoniaCliente;
    @FXML
    private Label lbCodigoPostalCliente;
    @FXML
    private Label lbCalleCliente;
    @FXML
    private Label lbNumeroCliente;
    @FXML
    private GridPane grdpDestinatario;
    @FXML
    private Label lbNombreDestinatario;
    @FXML
    private Label lbEstadoDestinatario;
    @FXML
    private Label lbCiudadDestinatario;
    @FXML
    private Label lbColoniaDestinatario;
    @FXML
    private Label lbCodigoPostalDestinatario;
    @FXML
    private Label lbCalleDestinatario;
    @FXML
    private Label lbNumeroDestinatario;
    @FXML
    private GridPane grdpSucursal;
    @FXML
    private Label lbNombreSucursal;
    @FXML
    private Label lbCUS;
    @FXML
    private Label lbEstadoSucursal;
    @FXML
    private Label lbCiudadSucursal;
    @FXML
    private Label lbColoniaSucursal;
    @FXML
    private Label lbCodigoPostalSucursal;
    @FXML
    private Label lbCalleSucursal;
    @FXML
    private Label lbNumeroSucursal;
    
    private Envio envio;
    private INotificador observador;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarDatos(Envio envio, INotificador observador){
        this.envio = envio;
        this.observador = observador;
        lbNoGuia.setText(envio.getNoGuia());
        if(envio.getIdConductor()==0){
            lbNombreConductor.setText("Sin Asignar");
            lbNoLicencia.setText("No aplica");
        }else{
            lbNombreConductor.setText(
                    envio.getNombreConductor()+" "+envio.getApellidoPatConductor()+" "+envio.getApellidoMatConductor());
            lbNoLicencia.setText(envio.getNoLicenciaConductor());
        }
        lbEstatus.setText(envio.getEstatus());
        lbTiempo.setText(envio.getTiempo());
        lbNombreColaborador.setText(
                envio.getNombreColaborador()+" "+envio.getApellidoPatColaborador()+" "+envio.getApellidoMatColaborador());
        lbMotivo.setText(envio.getMotivo());
        lbNombreCliente.setText(
                envio.getNombreCliente()+" "+envio.getApellidoPatCliente()+" "+envio.getApellidoMatCliente());
        lbTelefonoCliente.setText(envio.getTelefonoCliente());
        lbCorreoCliente.setText(envio.getCorreoCliente());
        lbColoniaCliente.setText(envio.getColoniaCliente());
        lbCodigoPostalCliente.setText(String.valueOf(envio.getCodigoPostalCliente()));
        lbCalleCliente.setText(envio.getCalleCliente());
        lbNumeroCliente.setText(String.valueOf(envio.getNumeroCliente()));
        lbNombreDestinatario.setText(envio.getNombreDest()+" "+envio.getApellidoPatDest()+" "+envio.getApellidoMatDest());
        lbEstadoDestinatario.setText(envio.getEstadoDest());
        lbCiudadDestinatario.setText(envio.getCiudadDest());
        lbColoniaDestinatario.setText(envio.getColoniaDest());
        lbCodigoPostalDestinatario.setText(String.valueOf(envio.getCodigoPostalDest()));
        lbCalleDestinatario.setText(envio.getCalleDest());
        lbNumeroDestinatario.setText(String.valueOf(envio.getNumDest()));
        lbNombreSucursal.setText(envio.getNombreSucursal());
        lbCUS.setText(envio.getCUSSucursal());
        lbEstadoSucursal.setText(envio.getEstadoSucursal());
        lbCiudadSucursal.setText(envio.getCiudadSucursal());
        lbColoniaSucursal.setText(envio.getColoniaSucursal());
        lbCodigoPostalSucursal.setText(String.valueOf(envio.getCodigoPostalSucursal()));
        lbCalleSucursal.setText(envio.getCalleSucursal());
        lbNumeroSucursal.setText(String.valueOf(envio.getNumeroSucursal()));
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        if (btnEnvio.getStyle().contains("-fx-base")) {
            irActualizarEstatus(envio);
        }else{
            irEditarEnvio(envio);
        }
        
    }

    @FXML
    private void btnEnvio(ActionEvent event) {
        grdpEnvio.setVisible(true);
        grdpRemitente.setVisible(false);
        grdpDestinatario.setVisible(false);
        grdpSucursal.setVisible(false);
        btnEnvio.setStyle("-fx-base: #03658c; -fx-border-color: #02354a; -fx-text-fill: #e1e1e1; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvEnvio.setFill(Color.web("e1e1e1"));
        btnRemitente.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvRemitente.setFill(Color.web("000"));
        btnDestinatario.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvDestinatario.setFill(Color.web("000"));
        btnSucursal.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvSucursal.setFill(Color.web("000"));
        
        btnActualizar.setText("Actualizar\nEstatus");
        btnActualizar.setVisible(true);
    }

    @FXML
    private void btnRemitente(ActionEvent event) {
        grdpEnvio.setVisible(false);
        grdpRemitente.setVisible(true);
        grdpDestinatario.setVisible(false);
        grdpSucursal.setVisible(false);
        btnRemitente.setStyle("-fx-base: #03658c; -fx-border-color: #02354a; -fx-text-fill: #e1e1e1; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvRemitente.setFill(Color.web("e1e1e1"));
        btnEnvio.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvEnvio.setFill(Color.web("000"));
        btnDestinatario.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvDestinatario.setFill(Color.web("000"));
        btnSucursal.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvSucursal.setFill(Color.web("000"));
        
        btnActualizar.setText("Actualizar\nDatos");
        btnActualizar.setVisible(true);
    }

    @FXML
    private void btnDestinatario(ActionEvent event) {
        grdpEnvio.setVisible(false);
        grdpRemitente.setVisible(false);
        grdpDestinatario.setVisible(true);
        grdpSucursal.setVisible(false);
        btnDestinatario.setStyle("-fx-base: #03658c; -fx-border-color: #02354a; -fx-text-fill: #e1e1e1; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvDestinatario.setFill(Color.web("e1e1e1"));
        btnEnvio.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvEnvio.setFill(Color.web("000"));
        btnRemitente.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvRemitente.setFill(Color.web("000"));
        btnSucursal.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvSucursal.setFill(Color.web("000"));
        
        btnActualizar.setText("Actualizar\nDatos");
        btnActualizar.setVisible(true);
    }

    @FXML
    private void btnSucursal(ActionEvent event) {
        grdpEnvio.setVisible(false);
        grdpRemitente.setVisible(false);
        grdpDestinatario.setVisible(false);
        grdpSucursal.setVisible(true);
        btnSucursal.setStyle("-fx-base: #03658c; -fx-border-color: #02354a; -fx-text-fill: #e1e1e1; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvSucursal.setFill(Color.web("e1e1e1"));
        btnEnvio.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvEnvio.setFill(Color.web("000"));
        btnDestinatario.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvDestinatario.setFill(Color.web("000"));
        btnRemitente.setStyle("-fx-border-color: #02354a; -fx-text-fill: #000000; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-insets: -1;");
        imgvRemitente.setFill(Color.web("000"));
        
        btnActualizar.setVisible(false);
    }
    
    private void irActualizarEstatus(Envio envio){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLActualizarEstatus.fxml"));
            Parent vista= cargador.load();
            FXMLActualizarEstatusController controlador= cargador.getController();
            controlador.cargarIdEnvio(envio, this);
            Scene escena= new Scene(vista);
            Stage escenario= new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Actualizar Estatus");    
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.setResizable(false);
            escenario.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void irEditarEnvio(Envio envio){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioEnvios.fxml"));
            Parent vista= cargador.load();
            FXMLFormularioEnviosController controlador= cargador.getController();
            controlador.inicializarDatos(envio, this);
            Scene escena= new Scene(vista);
            Stage escenario= (Stage )grdpDestinatario.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Actualizar Envio");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notificarOperacionExitosa(String operacion, String noGuia) {
        System.out.println("Operacion: "+operacion+", NoGuia: "+noGuia);
        inicializarDatos(mostrarInformacionActualizada(noGuia), this);
    }
    
    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionEnvios.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) grdpEnvio.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administracion Envios");          
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Envio mostrarInformacionActualizada(String noGuia){
        List<Envio> envioEncontrado = null;
        Envio envioActualizado = null;
        HashMap<String, Object> respuesta = EnvioImp.buscarEnvio(noGuia);
        if (!(boolean) respuesta.get(Constantes.KEY_ERROR)) {
            envioEncontrado = (List<Envio>) respuesta.get(Constantes.KEY_LISTA);
            if (envioEncontrado.size()==1) {
                envioActualizado = envioEncontrado.get(0);
            }
            return envioActualizado;
        }else{
            Utilidades.mostrarAlertaSimple("Error", ""+respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
            regresarVentana();
            return null;
        }
    }
    
}
