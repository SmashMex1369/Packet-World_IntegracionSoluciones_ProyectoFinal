package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import uv.tc.packetworldclientemovil.databinding.FragmentDestinatarioBinding
import uv.tc.packetworldclientemovil.utilidades.EnvioViewModel

class DestinatarioFragment : Fragment() {
    private var _binding: FragmentDestinatarioBinding? = null

    private val viewModelCompartido : EnvioViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinatarioBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val envio = viewModelCompartido.envioSeleccionado
        if (envio != null) {
            if (envio.apellidoMatDest==null){
                binding.tvNombreValor.text = "${envio.nombreDest} ${envio.apellidoPatDest}"
            }else{

                binding.tvNombreValor.text = "${envio.nombreDest} ${envio.apellidoPatDest} ${envio.apellidoMatDest}"
            }
            binding.tvEstadoValor.text = envio.estadoDest
            binding.tvCiudadValor.text = envio.ciudadDest
            binding.tvColoniaValor.text = envio.coloniaDest
            binding.tvCodigoPostalValor.text = envio.codigoPostalDest.toString()
            binding.tvCalleValor.text = envio.calleDest
            binding.tvNumeroValor.text = envio.numDest.toString()
        }else{
            Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}