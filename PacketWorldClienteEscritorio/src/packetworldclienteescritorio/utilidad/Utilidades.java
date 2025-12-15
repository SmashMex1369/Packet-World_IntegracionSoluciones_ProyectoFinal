package packetworldclienteescritorio.utilidad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Function;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import packetworldclienteescritorio.pojo.Direccion;

/**
 *
 * @author OmarVX
 */
public class Utilidades {
    
    public static String obtenerContenidoWS(InputStream inputWS) throws IOException{
 BufferedReader in = new BufferedReader(new InputStreamReader(inputWS));
 String inputLine;
 StringBuffer respuestaEntrada = new StringBuffer();
 while( (inputLine = in.readLine()) != null){
  respuestaEntrada.append(inputLine);
 }
 in.close();
 return respuestaEntrada.toString();
}
    
    public static void mostrarAlertaSimple(String titulo, String contenido, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
    
    public static boolean mostrarAlertaConfirmacion(String titulo, String contenido){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        Optional<ButtonType> btnSeleccion = alerta.showAndWait();
        return (btnSeleccion.get() == ButtonType.OK);
    }
    
    public static <T> void configurarComboBoxMostrarCampo(
        ComboBox<T> combo,
        Function<T, String> getter,
        ObservableList<T> items) {

    combo.setItems(items);

    combo.setCellFactory(lv -> new ListCell<T>() {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty || item == null ? null : getter.apply(item));
        }
    });

    combo.setButtonCell(new ListCell<T>() {
        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty || item == null ? null : getter.apply(item));
        }
    });
}


    
}
