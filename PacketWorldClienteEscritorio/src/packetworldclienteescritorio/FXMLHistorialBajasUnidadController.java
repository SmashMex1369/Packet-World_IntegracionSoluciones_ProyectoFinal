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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import packetworldclienteescritorio.dominio.UnidadImp;
import packetworldclienteescritorio.interfaz.INotificador;
import packetworldclienteescritorio.pojo.Unidad;
import packetworldclienteescritorio.utilidad.Constantes;
import packetworldclienteescritorio.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author citla
 */
public class FXMLHistorialBajasUnidadController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<Unidad> tvHistorialBajasUnidad;
    @FXML
    private TableColumn tcVIN;
    @FXML
    private TableColumn <Unidad, String> tcColaborador;
    @FXML
    private TableColumn tcTiempo;
    @FXML
    private TableColumn tcMotivo;
    
    private ObservableList<Unidad> unidades;
    private Unidad unidadEdicion;
    private INotificador observador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarHistorialUnidadesInactivas();
    }
    
    public void configurarTabla(){
        tcVIN.setCellValueFactory(new PropertyValueFactory("VIN"));
        tcColaborador.setCellValueFactory(cellData ->{
            Unidad u = cellData.getValue();
            String datos = u.getNombreColaborador()+ " " + u.getApellidoPatColaborador()+ " " + u.getApellidoMatColaborador();
            return new ReadOnlyStringWrapper(datos);
        });
        tcTiempo.setCellValueFactory(new PropertyValueFactory("tiempo"));
        tcMotivo.setCellValueFactory(new PropertyValueFactory("motivo"));
    }
    
    private void cargarHistorialUnidadesInactivas(){
        HashMap<String, Object> respuesta= UnidadImp.obtenerUnidadesInactivas();
        boolean esError= (boolean)respuesta.get(Constantes.KEY_ERROR);
        if(!esError){
            List<Unidad> unidadesAPI= (List<Unidad>) respuesta.get(Constantes.KEY_LISTA);
            for(int i=0;i<unidadesAPI.size();i++){
                if(unidadesAPI.get(i).getIdConductor()==0){
                    unidadesAPI.get(i).setNombreConductor("N/A");
                    unidadesAPI.get(i).setApellidoPatConductor("");
                    unidadesAPI.get(i).setApellidoMatConductor("");
                }
            }
            unidades= FXCollections.observableArrayList();
            unidades.addAll(unidadesAPI);
            tvHistorialBajasUnidad.setItems(unidades);
        }else{
            Utilidades.mostrarAlertaSimple("Error al cargar", ""+respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnRegresar(ActionEvent event) {
    }

    @FXML
    private void buscarUnidad(ActionEvent event) {
    }
    
}
