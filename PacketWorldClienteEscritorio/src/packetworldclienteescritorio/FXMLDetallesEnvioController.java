package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

/**
 * FXML Controller class
 *
 * @author alex4
 */
public class FXMLDetallesEnvioController implements Initializable {

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
    private Label lbCodigoPostal;
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
    private Label lbCodigoPostalSuursal;
    @FXML
    private Label lbCalleSucursal;
    @FXML
    private Label lbNumeroSucursal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegresar(ActionEvent event) {
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
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
    }

    @FXML
    private void btnDestinatario(ActionEvent event) {
    }

    @FXML
    private void btnSucursal(ActionEvent event) {
    }
    
}
