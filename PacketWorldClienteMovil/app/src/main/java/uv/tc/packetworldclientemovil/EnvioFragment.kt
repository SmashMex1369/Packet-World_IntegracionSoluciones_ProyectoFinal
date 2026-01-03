package uv.tc.packetworldclientemovil

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import uv.tc.packetworldclientemovil.databinding.FragmentEnvioBinding
import uv.tc.packetworldclientemovil.poko.Envio
import uv.tc.packetworldclientemovil.utilidades.EnvioViewModel

class EnvioFragment : Fragment() {
    // 1. Variable privada anulable (_binding).
    // Se usa solo internamente para poder ponerla en null al destruir la vista.
    private var _binding: FragmentEnvioBinding? = null

    // 2. Propiedad pública no anulable (binding).
    // Esta es la que usarás en tu código para acceder a los botones, textos, etc.
    // El 'get() = _binding!!' significa: "Dame el valor de _binding y asegúrate que no sea null".
    private val binding get() = _binding!!

    private val viewModelCompartido : EnvioViewModel by activityViewModels()
    private val launcherEdicion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == RESULT_OK){
            val envioSeleccionado = result.data?.getStringExtra("estatusEnvioActualizado")
            if (envioSeleccionado != null){
                val gson = Gson()
                viewModelCompartido.envioSeleccionado= gson.fromJson(envioSeleccionado, Envio::class.java)
            }
        }
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val envio = viewModelCompartido.envioSeleccionado
        if (envio != null) {
            binding.tvNoGuiaValor.text = envio.noGuia
            binding.tvEstatusValor.text = envio.estatus
            if (binding.tvMotivoValor!=null){
                binding.tvMotivoValor.text = envio.motivo
            }else{
                binding.tvMotivoValor.text = ""
            }
        }else{
            Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }

        binding.btnActualizarEstatus.setOnClickListener {
            val intent= Intent(requireContext(), ActualizarEstatusActivity::class.java)
            val gson = Gson()
            val idColaborador = requireActivity().intent.getIntExtra("idColaborador", 0)
            intent.putExtra("envio", gson.toJson(viewModelCompartido.envioSeleccionado))
            intent.putExtra("idColaborador", idColaborador)
            launcherEdicion.launch(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val envio = viewModelCompartido.envioSeleccionado
        if (envio != null) {
            binding.tvNoGuiaValor.text = envio.noGuia
            binding.tvEstatusValor.text = envio.estatus
            if (binding.tvMotivoValor!=null){
                binding.tvMotivoValor.text = envio.motivo
            }else{
                binding.tvMotivoValor.text = ""
            }
        }else{
            Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }
    }

    // 5. ¡MUY IMPORTANTE! Limpiar el binding
    //Debes tener cuidado con la memoria, es obligatorio limpiar el binding
    // cuando la vista se destruye (onDestroyView) para evitar fugas de memoria.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}