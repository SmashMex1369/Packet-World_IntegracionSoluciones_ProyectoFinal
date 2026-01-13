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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.UnidadImp;
import packetworldclienteescritorio.dto.Respuesta;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Colaborador;
import packetworldclienteescritorio.pojo.TipoUnidad;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Sesion;
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
    
    private Colaborador c;
    
    private Unidad unidadEdicion;
    private INotificador observador;
    private ObservableList<TipoUnidad> tipos;
    @FXML
    private Label lbTitulo;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposUnidad();
        c=Sesion.getColaborador();
    }

    public void iniciarDatos(Unidad unidadEdicion, INotificador observador){
        this.unidadEdicion= unidadEdicion;
        this.observador= observador;
        if(unidadEdicion!=null){
            tfModel.setText(unidadEdicion.getModelo());
            tfMarca.setText(unidadEdicion.getMarca());
            tfAnio.setText(String.valueOf(unidadEdicion.getAño()));
            tfVIN.setText(unidadEdicion.getVIN());
            int posicionTipoUnidad= obtenerPosicionTipoUnidad(unidadEdicion.getIdTipoUnidad());
            cbTipoUnidad.getSelectionModel().select(posicionTipoUnidad);
            lbTitulo.setText("Actualizar Unidad");
            btnGuardar.setText("Actualizar");
            tfVIN.setDisable(true);
        }else{
            lbTitulo.setText("Registrar Unidad");
            btnGuardar.setText("Crear");
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
        Respuesta respuestaVIN = UnidadImp.verificarVIN(tfVIN.getText());
        boolean validos = sonCamposValidos();
        if(validos&&(!respuestaVIN.isError()||unidadEdicion!=null)){
            Unidad unidad= new Unidad();
            unidad.setModelo(tfModel.getText());
            unidad.setMarca(tfMarca.getText());
            unidad.setAño(Integer.parseInt(tfAnio.getText()));
            unidad.setVIN(tfVIN.getText().toUpperCase());
            unidad.setNII(generarNII(Integer.parseInt(tfAnio.getText()), tfVIN.getText().toUpperCase()));
            System.out.println(unidad.getNII());
            TipoUnidad tipoUnidadSeleccionado= cbTipoUnidad.getSelectionModel().getSelectedItem();
            unidad.setIdTipoUnidad(tipoUnidadSeleccionado.getIdTipoUnidad());
            unidad.setIdColaborador(c.getIdColaborador());
            if(unidadEdicion==null){
                registrarUnidad(unidad);
            }else{
                editarUnidad(unidad);
            }
        }else if (!validos){
            Utilidades.mostrarAlertaSimple("Campos incorrectos", "Hay datos faltantes o no tienen el formato adecuado.", Alert.AlertType.ERROR);
        }else if (respuestaVIN.isError()) {
            Utilidades.mostrarAlertaSimple("VIN registrado", respuestaVIN.getMensaje(), Alert.AlertType.WARNING);
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
                if(Integer.parseInt(tfAnio.getText())<1901 || Integer.parseInt(tfAnio.getText())>2026){
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
        if((tfVIN.getText()==null || tfVIN.getText().isEmpty())||(tfVIN.getText().trim().length()>17||tfVIN.getText().trim().length()<17)){
            camposValidos=false;
            tfVIN.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1");
        }
        if(cbTipoUnidad.getSelectionModel().getSelectedIndex() == -1){
            camposValidos=false;
            cbTipoUnidad.setStyle("-fx-border-color: #bf0b0b; -fx-border-insets: -1; -fx-font-size: 21");
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
            Utilidades.remaximizar(escenario, escena);
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

    @FXML
    private void perderFoco(MouseEvent event) {
        tfMarca.getParent().requestFocus();
    }

}
