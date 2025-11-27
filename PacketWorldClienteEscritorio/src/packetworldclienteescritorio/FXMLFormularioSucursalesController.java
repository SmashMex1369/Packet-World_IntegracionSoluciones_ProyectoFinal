/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetworldclienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author OmarVX
 */
public class FXMLFormularioSucursalesController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<?> cbColonia;
    @FXML
    private ComboBox<?> cbCiudad;
    @FXML
    private ComboBox<?> cbEstado;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumeroExterior;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfCodigoUnico;
    @FXML
    private TextField tfNombreCorto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void clicBtnGuardar(ActionEvent event) {
    }
    
}
