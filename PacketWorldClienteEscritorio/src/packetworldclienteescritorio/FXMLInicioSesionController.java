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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.InicioSesionImp;
import packetworldclienteescritorio.dto.RSAutenticacionColaborador;
import packetworldclienteescritorio.utilidad.Utilidades;

public class FXMLInicioSesionController implements Initializable {

    @FXML
    private ImageView ivImg;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private TextField tfPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }

    @FXML
    private void btnIniciar(ActionEvent event) {
        
        String noPersonal= tfNoPersonal.getText();
        String password= tfPassword.getText();
        
        if(!noPersonal.isEmpty() && !password.isEmpty()){
            verificarCredenciales(noPersonal, password);
        }else{        
            Utilidades.mostrarAlertaSimple("Campos requeridos", "El no. Personal y/o contraseña son obligatorios", Alert.AlertType.WARNING);
        }
    }
    
    private void verificarCredenciales(String noPersonal, String password){
        RSAutenticacionColaborador respuesta= InicioSesionImp.verificarCredenciales(noPersonal, password);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Credenciales correctas", "Bienvenido(a) colaborador(a) "+respuesta.getColaborador().getNombre()+" al sistema", Alert.AlertType.INFORMATION);
            irMenuPrincipal();
        }else{
            System.out.println("error VC");
           Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }           
    }
    
    private void irMenuPrincipal(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Parent vista= cargador.load();
            //FXMLMenuPrincipalController controlador= cargador.getController();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfNoPersonal.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Menú Principal");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
