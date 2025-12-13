package packetworldclienteescritorio.utilidad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author OmarVX
 */
public class FiltradoTablas {
    
    public static <T> void configurarFiltradoGenerico(
            TextField campoBusqueda, 
            TableView<T> tabla, 
            ObservableList<T> listaOriginal,
            BuscadorGenerico<T> buscador) {
        
        // Validar que los parámetros no sean nulos
    if (campoBusqueda == null || tabla == null || buscador == null) {
        throw new IllegalArgumentException("Parámetros no pueden ser nulos");
    }
    
    // Si listaOriginal es null, crear una vacía
    if (listaOriginal == null) {
        listaOriginal = FXCollections.observableArrayList();
    }
    
    FilteredList<T> datosFiltrados = new FilteredList<>(listaOriginal, p -> true);
        
        
        campoBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            datosFiltrados.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return buscador.coincideConBusqueda(item, newValue.toLowerCase());
            });
        });
        
        SortedList<T> datosFiltradosYOrdenados = new SortedList<>(datosFiltrados);
        datosFiltradosYOrdenados.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(datosFiltradosYOrdenados);
    }
    
    @FunctionalInterface
    public interface BuscadorGenerico<T> {
        boolean coincideConBusqueda(T item, String filtro);
    }
    
}
