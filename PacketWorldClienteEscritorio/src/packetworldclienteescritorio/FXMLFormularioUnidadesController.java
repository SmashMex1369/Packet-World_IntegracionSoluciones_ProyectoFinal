package packetworldclienteescritorio;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.PaqueteImp;
import packetworldclienteescritorio.dominio.UnidadImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.TipoUnidad;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLFormularioUnidadesController implements Initializable {

    @FXML
    private ComboBox<TipoUnidad> cbTipoUnidad;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfAnio;
    @FXML
    private TextField tfVIN;
    @FXML
    private TextField tfNII;
    
    private Unidad unidadEdicion;
    private INotificador observador;
    private ObservableList<TipoUnidad> tipos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposUnidad();
    }

    public void iniciarDatos(Unidad unidadEdicion, INotificador observador){
        this.unidadEdicion= unidadEdicion;
        this.observador= observador;
        if(unidadEdicion!=null){
            tfModel.setText(unidadEdicion.getModelo());
            tfMarca.setText(unidadEdicion.getMarca());
            tfAnio.setText(String.valueOf(unidadEdicion.getAño()));
            tfVIN.setText(unidadEdicion.getVIN());
            tfNII.setText(unidadEdicion.getNII());
            int posicionTipoUnidad= obtenerPosicionTipoUnidad(unidadEdicion.getIdTipoUnidad());
            cbTipoUnidad.getSelectionModel().select(posicionTipoUnidad);
        }
    }
    
    private int obtenerPosicionTipoUnidad(int idTipoUnidad){
        for (int i=0; i<tipos.size(); i++){
            if(tipos.get(i).getIdTipoUnidad()==idTipoUnidad){
                return i;
            }
        }
        return -1;
    }
    
    private void cargarTiposUnidad(){
        HashMap<String, Object> respuesta= CatalogoImp.obtenerTiposUnidad();
        if(!(boolean) respuesta.get(Constantes.KEY_ERROR)){
            List<TipoUnidad> tiposUnidadAPI= (List<TipoUnidad>)respuesta.get(Constantes.KEY_LISTA);
            tipos= FXCollections.observableArrayList();
            tipos.addAll(tiposUnidadAPI);
            cbTipoUnidad.setItems(tipos);
        }else{
            Utilidades.mostrarAlertaSimple(Constantes.KEY_ERROR, respuesta.get(Constantes.KEY_MENSAJE).toString(), Alert.AlertType.ERROR);
            regresarVentana();
        }
    }
    
    private void registrarUnidad(Unidad unidad){
        Respuesta respuesta= UnidadImp.registrar(unidad);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Unidad registrado", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("registro", unidad.getVIN());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void editarUnidad(Unidad unidad){
        unidad.setIdUnidad(unidadEdicion.getIdUnidad());
        Respuesta respuesta= UnidadImp.editar(unidad);
        if(!respuesta.isError()){
            Utilidades.mostrarAlertaSimple("Unidad editada", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("edición", unidad.getVIN());
            regresarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        if(sonCamposValidos()){
            Unidad unidad= new Unidad();
            unidad.setModelo(tfModel.getText());
            unidad.setMarca(tfMarca.getText());
            unidad.setAño(Integer.parseInt(tfAnio.getText()));
            unidad.setVIN(tfVIN.getText());
            unidad.setNII(generarNII(Integer.parseInt(tfAnio.getText()), tfVIN.getText()));
            System.out.println(unidad.getNII());
            TipoUnidad tipoUnidadSeleccionado= cbTipoUnidad.getSelectionModel().getSelectedItem();
            unidad.setIdTipoUnidad(tipoUnidadSeleccionado.getIdTipoUnidad());
            if(unidadEdicion==null){
                registrarUnidad(unidad);
            }else{
                editarUnidad(unidad);
            }
        }
    }
    
    public static String generarNII(int año, String VIN){
        String NII;
        NII= año+VIN.substring(0, 4);
        return NII;
    }
    
    private boolean sonCamposValidos(){
        boolean camposValidos= true;
        if (tfModel.getText()==null || tfModel.getText().isEmpty()){         
            camposValidos=false;
            tfModel.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");       
        }
        if(tfMarca.getText()==null || tfMarca.getText().isEmpty()){
            camposValidos=false;
            tfMarca.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(tfAnio.getText()!=null || !tfAnio.getText().isEmpty()){
            try {
                if(Integer.parseInt(tfAnio.getText())<1800 || Integer.parseInt(tfAnio.getText())>2027){
                    camposValidos=false;
                    tfAnio.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
                }
            } catch (NumberFormatException e) {
                camposValidos=false;
                tfAnio.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
            }
        }else{
            camposValidos=false;
            tfAnio.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }       
        if(tfVIN.getText()==null || tfVIN.getText().isEmpty()){
            camposValidos=false;
            tfVIN.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(cbTipoUnidad.getSelectionModel().getSelectedIndex() == -1){
            camposValidos=false;
            cbTipoUnidad.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1; -fx-font-size: 21");
        }
        if(!camposValidos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes o no tienen el formato adecuado.", Alert.AlertType.ERROR);
        }
        return camposValidos;
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
        regresarVentana();
    }

    private void regresarVentana(){
        try {
            FXMLLoader cargador= new FXMLLoader(getClass().getResource("FXMLAdministracionUnidades.fxml"));
            Parent vista= cargador.load();
            Scene escena= new Scene(vista);
            Stage escenario= (Stage) tfModel.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Administración Unidades");
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tfMarcaTexto(KeyEvent event) {
        tfMarca.setStyle(null);
    }

    @FXML
    private void tfModeloTexto(KeyEvent event) {
        tfModel.setStyle(null);
    }

    @FXML
    private void tfAñoTexto(KeyEvent event) {
        tfAnio.setStyle(null);
    }

    @FXML
    private void cbTipoUnidadSeleccion(Event event) {
        cbTipoUnidad.setStyle("-fx-font-size: 21");
    }

    @FXML
    private void tfVINTexto(KeyEvent event) {
        tfVIN.setStyle(null);
    }

}
