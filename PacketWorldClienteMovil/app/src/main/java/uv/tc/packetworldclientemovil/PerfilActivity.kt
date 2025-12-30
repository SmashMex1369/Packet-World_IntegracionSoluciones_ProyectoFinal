package uv.tc.packetworldclientemovil

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarEstatusBinding
import uv.tc.packetworldclientemovil.databinding.ActivityPerfilBinding
import uv.tc.packetworldclientemovil.dto.RSAutenticacionConductor
import uv.tc.packetworldclientemovil.poko.Conductor
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets
import uv.tc.packetworldclientemovil.utilidades.descargarFoto

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private lateinit var conductor: Conductor
    val gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)

        cargarDatosConductor(intent.getStringExtra("conductor"))
        descargarFoto(conductor.idColaborador, this@PerfilActivity, binding.imgvPerfil)

        binding.imgbtnEditar.setOnClickListener {
            val intent = Intent(this, ActualizarPerfilActivity::class.java)
            intent.putExtra("conductor", gson.toJson(conductor))
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        recargarDatos()
        descargarFoto(conductor.idColaborador, this@PerfilActivity, binding.imgvPerfil)
    }

    private fun recargarDatos(){
        val prefs = getSharedPreferences("DatosConductor", MODE_PRIVATE)
        cargarDatosConductor(prefs.getString("conductor", null))
    }

    private fun cargarDatosConductor(jsonConductor: String?){
        if (jsonConductor != null){
            conductor = gson.fromJson(jsonConductor, Conductor::class.java)
            if (conductor.apellidoMaterno == null){
                binding.tvNombreValor.text = "${conductor.nombre} ${conductor.apellidoPaterno}"
            }else{
                binding.tvNombreValor.text = "${conductor.nombre} ${conductor.apellidoPaterno} ${conductor.apellidoMaterno}"
            }
            binding.tvCurpValor.text = conductor.CURP
            binding.tvCorreoValor.text = conductor.correo
            binding.tvNoLicenciaValor.text = conductor.noLicencia
            binding.tvNoPersonalValor.text = conductor.noPersonal
            binding.tvSucursalValor.text = conductor.CUS
        }
    }


}