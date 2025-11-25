package uv.tc.packetworldclientemovil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import uv.tc.packetworldclientemovil.databinding.ActivityEnviosBinding

class EnviosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnviosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnviosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}