package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uv.tc.packetworldclientemovil.databinding.FragmentEnvioBinding

class EnvioFragment : Fragment() {
    // 1. Variable privada anulable (_binding).
    // Se usa solo internamente para poder ponerla en null al destruir la vista.
    private var _binding: FragmentEnvioBinding? = null

    // 2. Propiedad pública no anulable (binding).
    // Esta es la que usarás en tu código para acceder a los botones, textos, etc.
    // El 'get() = _binding!!' significa: "Dame el valor de _binding y asegúrate que no sea null".
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 3. Inflamos usando el metodo 'inflate' de la clase Binding generada
        _binding = FragmentEnvioBinding.inflate(inflater, container, false)

        // Aquí ya puedes configurar cosas iniciales si quieres

        // 4. Retornamos la raíz (root) que es tu Layout principal (Constraint, Frame, etc.)
        return binding.root
    }

    // 5. ¡MUY IMPORTANTE! Limpiar el binding
    //Debes tener cuidado con la memoria, es obligatorio limpiar el binding
    // cuando la vista se destruye (onDestroyView) para evitar fugas de memoria.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}