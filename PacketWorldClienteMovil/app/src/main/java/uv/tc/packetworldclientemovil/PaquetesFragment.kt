package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uv.tc.packetworldclientemovil.adaptadores.PaquetesAdapter
import uv.tc.packetworldclientemovil.databinding.FragmentPaquetesBinding
import uv.tc.packetworldclientemovil.utilidades.EnvioViewModel
import kotlin.getValue

class PaquetesFragment : Fragment() {
    private var _binding: FragmentPaquetesBinding? = null

    private val binding get() = _binding!!

    private val viewModelCompartido : EnvioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaquetesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val envio = viewModelCompartido.envioSeleccionado
        if (envio != null) {
            binding.rvDescripcion.layoutManager = LinearLayoutManager(requireContext())
            val paquetes = envio.paquetes
            binding.tvNoPaquetesValor.text = paquetes.size.toString()
            binding.rvDescripcion.adapter = PaquetesAdapter(paquetes)
        }else{
            Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}