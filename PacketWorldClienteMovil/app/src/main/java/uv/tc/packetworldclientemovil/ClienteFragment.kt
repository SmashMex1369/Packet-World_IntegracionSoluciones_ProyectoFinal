package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import uv.tc.packetworldclientemovil.databinding.FragmentClienteBinding
import uv.tc.packetworldclientemovil.utilidades.EnvioViewModel

class ClienteFragment : Fragment() {
    private var _binding: FragmentClienteBinding? = null

    private val binding get() = _binding!!

    private val viewModelCompartido : EnvioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClienteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val envio = viewModelCompartido.envioSeleccionado
        if (envio != null) {
            if (envio.apellidoMatCliente==null){
                binding.tvNombreValor.text = "${envio.nombreCliente} ${envio.apellidoPatCliente}"
            }else{
                binding.tvNombreValor.text = "${envio.nombreCliente} ${envio.apellidoPatCliente} ${envio.apellidoMatCliente}"
            }
            binding.tvCorreoValor.text = envio.correoCliente
            binding.tvTelefonoValor.text = envio.telefonoCliente
        }else{
            Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}