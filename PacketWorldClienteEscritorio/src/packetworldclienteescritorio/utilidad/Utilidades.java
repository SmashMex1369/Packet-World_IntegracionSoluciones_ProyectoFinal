package packetworldclienteescritorio.utilidad;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Function;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javax.naming.Context;
import packetworldclienteescritorio.FXMLFormularioEditarColaboradoresController;
import packetworldclienteescritorio.FXMLMenuPrincipalController;
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

    public static void colocarImagen(String stringBase64, ImageView imagenPerfil, Class<?> claseReferencia){
        if (stringBase64.contains(",")){
            stringBase64 = stringBase64.split(",")[1];
        }
        
        byte[] fotoBytes = Base64.getMimeDecoder().decode(stringBase64);
        ByteArrayInputStream bis = new ByteArrayInputStream(fotoBytes);
        Image imagen = null;
        if (claseReferencia==FXMLMenuPrincipalController.class){
            imagen = new Image(bis, 0, 64, true, true);
        }else if (claseReferencia==FXMLFormularioEditarColaboradoresController.class){
            imagen = new Image(bis, 0, 100, true, true);
        }
        imagenPerfil.setImage(imagen);
        aplicarClipImagen(imagenPerfil, claseReferencia);
    }
    
    public static void cargarImagenPorDefecto(ImageView imagenPerfil, Class<?> claseReferencia){
        try {
            InputStream is = Utilidades.class.getResourceAsStream("/recursos/circle-user-solid.png");
            if (is != null) {
                Image imagen = null;
                if (claseReferencia==FXMLMenuPrincipalController.class){
                    imagen = new Image(is, 0, 64, true, true);
                }else if (claseReferencia==FXMLFormularioEditarColaboradoresController.class){
                    imagen = new Image(is, 0, 100, true, true);
                }
                imagenPerfil.setImage(imagen);
                aplicarClipImagen(imagenPerfil, claseReferencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void aplicarClipImagen(ImageView imagenPerfil, Class<?> claseReferencia){
        Circle clip = null;
        if (claseReferencia==FXMLMenuPrincipalController.class) {    
            clip = new Circle(imagenPerfil.getImage().getWidth()/2, 32, 32);
        }else if (claseReferencia==FXMLFormularioEditarColaboradoresController.class) {
            clip = new Circle(imagenPerfil.getImage().getWidth()/2, 50, 50);
        }
        imagenPerfil.setClip(clip);
    }
    
}
