package packetworldclienteescritorio;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.InicioSesionImp;
import packetworldclienteescritorio.dto.RSAutenticacionColaborador;
import packetworldclienteescritorio.utilidad.Sesion;
import packetworldclienteescritorio.utilidad.Utilidades;

public class FXMLInicioSesionController implements Initializable {

    @FXML
    private ImageView ivImg;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lblErrorNoPersonal;
    @FXML
    private Label lblErrorPass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pfPassword.setPromptText("\u2022\u2022\u2022\u2022\u2022");
    }

    @FXML
    private void btnIniciar(ActionEvent event) {
        
        String noPersonal= tfNoPersonal.getText();
        String contraseña= pfPassword.getText();
        
        if(!noPersonal.isEmpty() && !contraseña.isEmpty() || validarCampos(noPersonal, contraseña)){
            verificarCredenciales(noPersonal, contraseña);
        }else{        
            Utilidades.mostrarAlertaSimple("Campos requeridos", "El no. Personal y/o contraseña son obligatorios", Alert.AlertType.WARNING);
        }
    }
    
    // LBL's para error en el inicio de sesion
    private boolean validarCampos(String noPersonal, String contraseña){
        boolean camposValidos = true;
        lblErrorNoPersonal.setText("");
        lblErrorPass.setText("");
        if(noPersonal.isEmpty()){
            camposValidos = false;
            lblErrorNoPersonal.setText("No.Personal Obligatorio");
            tfNoPersonal.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(contraseña.isEmpty()){
            camposValidos = false;
            lblErrorPass.setText("Contraseña Obligatoria");
            pfPassword.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        return camposValidos;
    }
    
    private void verificarCredenciales(String noPersonal, String contraseña){
        RSAutenticacionColaborador respuesta= InicioSesionImp.verificarCredenciales(noPersonal, contraseña);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Credenciales correctas", "Bienvenido(a) colaborador(a) "+respuesta.getColaborador().getNombre()+" al sistema", Alert.AlertType.INFORMATION);
            Sesion.iniciarSesion(respuesta.getColaborador());
            irMenuPrincipal();
        }else{
            Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }           
    }
    
    private void irMenuPrincipal(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfNoPersonal.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setResizable(true);
            escenario.setTitle("Menú Principal");
            escenario.centerOnScreen();
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void perderFoco(MouseEvent event) {
        tfNoPersonal.getParent().requestFocus();
    }

    @FXML
    private void tfNoPersonalTexto(KeyEvent event) {
        tfNoPersonal.setStyle(null);
        lblErrorNoPersonal.setText("");
    }

    @FXML
    private void pfPasswordTexto(KeyEvent event) {
        pfPassword.setStyle(null);
        lblErrorPass.setText("");
    }
}
