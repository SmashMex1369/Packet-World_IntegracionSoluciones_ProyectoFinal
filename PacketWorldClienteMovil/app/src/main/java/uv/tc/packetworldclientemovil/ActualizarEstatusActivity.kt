package uv.tc.packetworldclientemovil

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarEstatusBinding
import uv.tc.packetworldclientemovil.databinding.ActivityEnviosBinding
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets

class ActualizarEstatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarEstatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarEstatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
        binding.spnEstatus.setAdapter(ArrayAdapter(this,R.layout.custom_spinner_items,resources.getStringArray(R.array.spn_estatus)))
    }
}