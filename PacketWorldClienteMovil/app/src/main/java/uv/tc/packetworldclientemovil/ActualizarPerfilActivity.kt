package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarPerfilBinding
import uv.tc.packetworldclientemovil.databinding.ActivityPerfilBinding
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets

class ActualizarPerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()

        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)
    }
}