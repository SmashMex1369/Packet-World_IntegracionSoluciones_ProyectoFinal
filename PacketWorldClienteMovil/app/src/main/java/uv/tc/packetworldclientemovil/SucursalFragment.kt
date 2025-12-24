package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import uv.tc.packetworldclientemovil.databinding.FragmentSucursalBinding
import uv.tc.packetworldclientemovil.utilidades.EnvioViewModel
import kotlin.getValue


class SucursalFragment : Fragment() {
    private var _binding: FragmentSucursalBinding? = null

    private val binding get() = _binding!!

    private val viewModelCompartido : EnvioViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSucursalBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val envio = viewModelCompartido.envioSeleccionado
        if (envio != null) {
            binding.tvSucursalValor.text = envio.nombreSucursal
            binding.tvCusValor.text = envio.CUSSucursal
            binding.tvEstadoValor.text = envio.estadoSucursal
            binding.tvCiudadValor.text = envio.ciudadSucursal
            binding.tvColoniaValor.text = envio.coloniaSucursal
            binding.tvCodigoPostalValor.text = envio.codigoPostalSucursal.toString()
            binding.tvCalleValor.text = envio.calleSucursal
            binding.tvNumeroValor.text = envio.numeroSucursal.toString()
        }else{
            Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}