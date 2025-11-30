package uv.tc.packetworldclientemovil

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import uv.tc.packetworldclientemovil.databinding.ActivityEnviosBinding
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets

class EnviosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnviosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnviosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
        binding.srlRecargar.setOnRefreshListener {
            //Aqui se llamara a la funcion que se encarge de recargar los envios
            binding.srlRecargar.isRefreshing = false
        }
    }
}