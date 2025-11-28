package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uv.tc.packetworldclientemovil.databinding.FragmentDestinatarioBinding
import uv.tc.packetworldclientemovil.databinding.FragmentPaquetesBinding

class PaquetesFragment : Fragment() {
    private var _binding: FragmentPaquetesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaquetesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}