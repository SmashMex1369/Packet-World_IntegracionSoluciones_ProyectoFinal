package uv.tc.packetworldclientemovil

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarEstatusBinding
import uv.tc.packetworldclientemovil.databinding.ActivityPerfilBinding
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
    }
}