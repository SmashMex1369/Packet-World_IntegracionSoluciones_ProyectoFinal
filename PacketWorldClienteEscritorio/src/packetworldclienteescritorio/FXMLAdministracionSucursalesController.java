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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author OmarVX
 */
public class FXMLAdministracionSucursalesController implements Initializable {

    @FXML
    private TableView<?> tvSucursales;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colEstatus;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TextField tfBusqueda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIrRegistrarSucursal(ActionEvent event) {
    }

    @FXML
    private void clicIrEditarSucursal(ActionEvent event) {
    }

    @FXML
    private void clicBajaSucursal(ActionEvent event) {
    }
    
}
