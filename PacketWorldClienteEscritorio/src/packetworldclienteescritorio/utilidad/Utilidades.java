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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.naming.Context;
import packetworldclienteescritorio.FXMLFormularioEditarColaboradoresController;
import packetworldclienteescritorio.FXMLMenuPrincipalController;
import packetworldclienteescritorio.dominio.CatalogoImp;
import packetworldclienteescritorio.dominio.EnvioImp;
import packetworldclienteescritorio.dto.RespuestaCosto;
import packetworldclienteescritorio.pojo.Direccion;
import packetworldclienteescritorio.pojo.Envio;

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
    
    public static Float calcularCosto(Envio envio, String codigoPostalDestinatario){
        Float costo = 0f;
        Integer codigoOrigen = CatalogoImp.obtenerCodigoPostalOrigen(envio.getIdSucursal());
        Integer codigoDestinatario = Integer.parseInt(codigoPostalDestinatario);
        String codigoPostalOrigen = "";
        String codigoPostalDest = "";
        if (codigoOrigen<10000) {
            codigoPostalOrigen = "0"+ codigoOrigen;
        }else{
            codigoPostalOrigen = codigoOrigen.toString();
        }
        if (codigoDestinatario<10000) {
            codigoPostalDest = "0"+ codigoDestinatario;
        }else{
            codigoPostalDest = codigoDestinatario.toString();
        }
        RespuestaCosto respuesta = EnvioImp.calcularDistancia(codigoPostalOrigen, codigoPostalDest);
        if (!respuesta.isError()){
            if (respuesta.getDistanciaKM()>0 && respuesta.getDistanciaKM()<201) {
                costo = respuesta.getDistanciaKM()*4;
            }else if(respuesta.getDistanciaKM()>=201&&respuesta.getDistanciaKM()<501){
                costo = respuesta.getDistanciaKM()*3;
            }else if(respuesta.getDistanciaKM()>=501&&respuesta.getDistanciaKM()<1001){
                costo = respuesta.getDistanciaKM()*2;
            }else if(respuesta.getDistanciaKM()>=1001&&respuesta.getDistanciaKM()<2001){
                costo = respuesta.getDistanciaKM()*1;
            }else if (respuesta.getDistanciaKM()>=2001) {
                costo = respuesta.getDistanciaKM()*0.5f;
            }else{
                costo = 0f;
            }
        }else{
            costo = 0f;
        }
        if (costo==0) {
            Utilidades.mostrarAlertaSimple("Problemas al calcular", "Se detecto un problema al calcular la distancia, el costo por distancia sera gratuito.", Alert.AlertType.INFORMATION);
        }else{
            Utilidades.mostrarAlertaSimple("Costo por distancia", "El costo por distancia sera de $"+costo, Alert.AlertType.INFORMATION);
        }
        if (envio.getPaquetes()==null) {
            return costo;
        }
        switch(envio.getPaquetes().size()){
            case 0:
                costo = costo + 0;
                break;
            case 1:
                costo = costo + 0;
                break;
            case 2:
                costo = costo + 50;
                break;
            case 3:
                costo = costo + 80;
                break;
            case 4:
                costo = costo + 110;
                break;
            default:
                costo = costo + 150;
                break;
        }
        mostrarAlertaSimple("Total de costo", "El total a pagar con " +envio.getPaquetes().size()+" paquetes es de $"+costo, Alert.AlertType.INFORMATION);

//        if (costo > 0) {
//        }else{
//            switch(envio.getPaquetes().size()){
//                case 0:
//                    costo = 0f;
//                    break;
//                case 1:
//                    costo = 50f;
//                    break;
//                case 2: 
//                    costo = 100f;
//                    break;
//                case 3:
//                    costo = 160f;
//                    break;
//                case 4:
//                    costo = 220f;
//                    break;
//                default:
//                    costo = 300f;
//                    break;
//            }
//            mostrarAlertaSimple("Total de costo", "El total a pagar de con " +envio.getPaquetes().size()+" paquetes son $"+costo, Alert.AlertType.INFORMATION);
//        }
        
        return costo;
    }
    
    public static void remaximizar(Stage stage, Scene scene){
        stage.setScene(scene);
        if (stage.isMaximized()){
            stage.setMaximized(false); 
            stage.setMaximized(true);
        }
    }
}
