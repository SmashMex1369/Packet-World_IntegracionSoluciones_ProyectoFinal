package uv.tc.packetworldclientemovil

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import uv.tc.packetworldclientemovil.adaptadores.EnviosAdapter
import uv.tc.packetworldclientemovil.databinding.ActivityEnviosBinding
import uv.tc.packetworldclientemovil.dto.RSAutenticacionConductor
import uv.tc.packetworldclientemovil.poko.Conductor
import uv.tc.packetworldclientemovil.poko.Envio
import uv.tc.packetworldclientemovil.utilidades.Constantes
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets
import uv.tc.packetworldclientemovil.utilidades.descargarFoto

class EnviosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnviosBinding
    private lateinit var conductor: Conductor
    private val gson = Gson()

    private lateinit var prefs: SharedPreferences

    private var posicionSeleccionada : Int = -1
    private lateinit var adapter: EnviosAdapter

    private val launcherEditar = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK){
            val envioEditado = gson.fromJson(result.data?.getStringExtra("envioEditado"), Envio::class.java)
            if (envioEditado != null && posicionSeleccionada != -1) {
                adapter.actualizarItem(posicionSeleccionada, envioEditado)
            }
            recargarEnvios()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnviosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)

        prefs = getSharedPreferences("DatosConductor", MODE_PRIVATE)
        val respuestaLogin: RSAutenticacionConductor = gson.fromJson(
            intent.getStringExtra("conductor"), RSAutenticacionConductor::class.java)
        conductor = respuestaLogin.conductor!!
        descargarFoto(conductor.idColaborador, this@EnviosActivity, binding.imgbtnPerfil)
        prefs.edit().putString("conductor", gson.toJson(conductor)).apply()

        binding.srlRecargar.setOnRefreshListener {
            recargarEnvios()
        }
        binding.srlRecargar.isRefreshing = true
        recargarEnvios()

        binding.imgbtnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this@EnviosActivity)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Desea cerrar la sesión actual?\nDeberá volver a iniciar sesión")
                .setCancelable(false)
                .setPositiveButton("Si"){_, _ ->
                    val preferencias = getSharedPreferences("Sesion", MODE_PRIVATE)
                    preferencias.edit {
                        clear()
                        apply()
                    }
                    val intent= Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("No"){dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.imgbtnPerfil.setOnClickListener {
            val jsonConductor = gson.toJson(conductor)
            val intent= Intent(this, PerfilActivity::class.java)
            intent.putExtra("conductor", jsonConductor)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        conductor = gson.fromJson(prefs.getString("conductor", null), Conductor::class.java)
        descargarFoto(conductor.idColaborador, this@EnviosActivity, binding.imgbtnPerfil)
    }

    private fun recargarEnvios(){
        binding.srlRecargar.isRefreshing = true
        obtenerEnvios()
    }

    private fun obtenerEnvios(){
        Ion.with(this@EnviosActivity)
            .load(Constantes().PETICION_GET, "${Constantes().URL_API}envio/obtener-envios-conductor/${conductor.idConductor}")
            .asString(Charsets.UTF_8)
            .setCallback { e, result ->
                if (e == null) {
                    serealizarRespuestaEnvios(result)
                }else{
                    Log.e("Error", "Obtener Envios: "+e.toString())
                    Toast.makeText(this@EnviosActivity, "Error: $e", Toast.LENGTH_LONG).show()
                    binding.srlRecargar.isRefreshing = false
                }
            }
    }

    private fun serealizarRespuestaEnvios(json: String){
        try {
            if (json.isNotEmpty()){
                val tipoLista = object : TypeToken<List<Envio>>() {}.type
                val envios: List<Envio> = gson.fromJson(json, tipoLista)
                if (envios.isNotEmpty()){
                    configurarRecyclerView(envios)
                }else{
                    configurarRecyclerView(envios)
                    Toast.makeText(this@EnviosActivity, "Actualmente no tiene envios pendientes", Toast.LENGTH_LONG).show()
                    binding.srlRecargar.isRefreshing = false
                }
            }
        }catch (e: Exception) {
            Log.e("Error", "Serealizar Respuesta Envios: "+e.toString())
            Toast.makeText(this@EnviosActivity, "Error $e", Toast.LENGTH_LONG).show()
            binding.srlRecargar.isRefreshing = false
        }

    }

    private fun configurarRecyclerView(envios: List<Envio>){
        binding.rvEnvios.layoutManager = LinearLayoutManager(this@EnviosActivity)
        adapter = EnviosAdapter(envios.toMutableList()) { envioSeleccionado, position ->
            posicionSeleccionada = position
            val jsonEnvio = gson.toJson(envioSeleccionado)
            envioSeleccionado.idColaborador= conductor.idColaborador
            val intent = Intent(this@EnviosActivity, DetallesActivity::class.java)
            intent.putExtra("idColaborador", conductor.idColaborador)
            intent.putExtra("envio", jsonEnvio)
            launcherEditar.launch(intent)
        }
        binding.rvEnvios.adapter =adapter
        binding.srlRecargar.isRefreshing = false
    }
}