/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.SucursalImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Sucursal;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author OmarVX
 */
public class FXMLAdministracionSucursalesController implements Initializable, INotificador {

    @FXML
    private TableView<Sucursal> tvSucursales;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn<Sucursal, String> colEstatus;
    @FXML
    private TableColumn<Sucursal, String> colDireccion = new TableColumn<>();
    @FXML
    private TextField tfBusqueda;
    
    private ObservableList<Sucursal> sucursales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInfoSucursales();
    }    
    
    private void configurarTabla(){
        colCodigo.setCellValueFactory(new PropertyValueFactory("CUS"));
        colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        colEstatus.setCellValueFactory(cellData -> {
            Sucursal s = cellData.getValue();
            String estatusTexto = s.getEstatus() == 1 ? "Activa" : "Inactiva";
            return new ReadOnlyStringWrapper(estatusTexto);
        });
        colDireccion.setCellValueFactory(cellData -> {
        Sucursal s = cellData.getValue();
        String combinado = "Calle " + s.getCalle() + " No." + s.getNumero() + " Colonia " + s.getColonia();
        return new ReadOnlyStringWrapper(combinado);
        });
    }
    
    
    private void cargarInfoSucursales(){
        
        HashMap<String, Object> respuesta= SucursalImp.obtenerTodos();
        boolean esError= (boolean) respuesta.get("error");
        if (!esError){
            List<Sucursal> sucursalesAPI= (List<Sucursal>)respuesta.get("sucursales");
            sucursales = FXCollections.observableArrayList();
            sucursales.addAll(sucursalesAPI);
            tvSucursales.setItems(sucursales);
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    private void clicIrRegistrarSucursal(ActionEvent event) {
        irFormularioSucursal(null);
    }

    @FXML
    private void clicIrEditarSucursal(ActionEvent event) {
        Sucursal sucursal = tvSucursales.getSelectionModel().getSelectedItem();
        if(sucursal != null){
            irFormularioSucursal(sucursal);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione una sucursal", "Para editar una sucursal debes seleccionar una de la tabla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicBajaSucursal(ActionEvent event) {
        Sucursal sucursal = tvSucursales.getSelectionModel().getSelectedItem();
        if(sucursal != null){
            boolean confirmarOperacion= Utilidades.mostrarAlertaConfirmacion("Dar de baja sucursal", "¿Estás seguro de dar de baja a la sucursal seleccionada?");
            if(confirmarOperacion){
                bajaSucursal(sucursal.getIdSucursal());
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccione una sucursal", "Para dar de baja una sucursal debes seleccionar una de la tabla", Alert.AlertType.WARNING);
        }
    }
    
    private void bajaSucursal(int idSucursal){
        Respuesta respuesta= SucursalImp.darBajaSucursal(idSucursal);
        System.out.println("funcion baja"+idSucursal);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Sucursal dada de baja", "Baja de sucursal exitosa.", Alert.AlertType.INFORMATION);
            cargarInfoSucursales();
        }else{
            Utilidades.mostrarAlertaSimple("Error al eliminar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tvSucursales.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Menú principal");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void irFormularioSucursal(Sucursal sucursal){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLFormularioSucursales.fxml"));
            Parent vista= cargador.load();
            FXMLFormularioSucursalesController controlador= cargador.getController();
            controlador.inicializarDatosSucursales(sucursal, this);
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tvSucursales.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Formulario sucursales");  
            //escenario.initModality(Modality.APPLICATION_MODAL);            
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void notificarOperacionExitosa(String operacion, String descripcion){
        System.out.println("Operacion: "+operacion+", descripcion del profesor: "+descripcion);
        cargarInfoSucursales();
    }
    
}
