package uv.tc.packetworldclientemovil

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarEstatusBinding
import uv.tc.packetworldclientemovil.dto.Respuesta
import uv.tc.packetworldclientemovil.poko.Envio
import uv.tc.packetworldclientemovil.poko.EstatusEnvio
import uv.tc.packetworldclientemovil.utilidades.Constantes
import uv.tc.packetworldclientemovil.utilidades.EnvioViewModel
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets
import java.nio.charset.Charset
import kotlin.collections.get
import kotlin.getValue

class ActualizarEstatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarEstatusBinding
    private var idEstatusEnvio = 0
    private lateinit var listaEstatusFiltrada : List<EstatusEnvio>
    private val gson = Gson()
    private lateinit var envio: Envio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarEstatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
        envio = gson.fromJson(intent.getStringExtra("envio"), Envio::class.java)
        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)
        binding.spnEstatus.addTextChangedListener { text ->
            if (text.toString().isNotEmpty()) {
                binding.tilSpinner.error = null
            }
        }
        binding.etMotivo.addTextChangedListener { text ->
            if (text.toString().isNotEmpty()) {
                binding.tilMotivo.error = null
            }
        }
    }

    override fun onStart() {
        super.onStart()
        cargarEstatusEnvioAPI()
        binding.spnEstatus.setOnItemClickListener { parent, view, position, id ->

            val estatusSeleccionado = listaEstatusFiltrada[position]

            if (estatusSeleccionado.idEstatusEnvio==4||estatusSeleccionado.idEstatusEnvio==6){
                binding.tilMotivo.hint = "Obligatorio"
            }else{
                binding.tilMotivo.hint = "Opcional"
            }
            idEstatusEnvio = estatusSeleccionado.idEstatusEnvio
            envio.estatus = estatusSeleccionado.estatus
        }

        binding.btnActualizar.setOnClickListener {

            envio.idColaborador = intent.getIntExtra("idColaborador", 0)
            envio.idEstatusEnvio = idEstatusEnvio
            envio.motivo = binding.etMotivo.text.toString()
            if(validarCampos()) {
                val jsonEnvio = gson.toJson(envio)
                actualizarEstatusAPI(jsonEnvio)
            }
        }
    }

    fun validarCampos():Boolean{
        if(envio.idEstatusEnvio>0){
            if (envio.idEstatusEnvio==6 || envio.idEstatusEnvio==4){
                if (envio.motivo.isNotEmpty()){
                    return true
                }else{
                    binding.tilMotivo.error = "Faltante"
                    Toast.makeText(this@ActualizarEstatusActivity, "Obligatorio un motivo", Toast.LENGTH_LONG).show()
                    return false
                }
            }else{
                return true
            }
        }else{
            binding.tilSpinner.error = "Faltante"
            Toast.makeText(this@ActualizarEstatusActivity, "Seleccione un estatus", Toast.LENGTH_LONG).show()
            return false
        }

    }

    fun cargarEstatusEnvioComboBox(json: String){
        try {
            if(json.isNotEmpty()){
                val gson= Gson()

                val tipoLista= object : TypeToken<List<EstatusEnvio>>(){}.type;
                val listaEstatus: List<EstatusEnvio> = gson.fromJson(json, tipoLista);
                listaEstatusFiltrada = listaEstatus.drop(2)
                val adapter= ArrayAdapter(
                    this,
                    R.layout.item_custom_spinner,
                    listaEstatusFiltrada.map { it.estatus }
                )

                binding.spnEstatus.setAdapter(adapter)

            }
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun cargarEstatusEnvioAPI(){
        Ion.with(this@ActualizarEstatusActivity)
            .load(Constantes().PETICION_GET, "${Constantes().URL_API}catalogo/obtener-estatus-envio")
            .asString(Charsets.UTF_8)
            .setCallback { e, result ->
                if(e==null){
                    cargarEstatusEnvioComboBox(result)
                }else{
                    Toast.makeText(this@ActualizarEstatusActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun actualizarEstatusAPI(json:String ){
        Ion.with(this@ActualizarEstatusActivity)
            .load(Constantes().PETICION_POST, "${Constantes().URL_API}envio/actualizar-estatus")
            .setHeader(Constantes().HEADER_CONTENT_TYPE, Constantes().TIPO_JSON)
            .setStringBody(json)
            .asString(Charsets.UTF_8)
            .setCallback { e, result ->
                if (e==null){
                    serializarRespuesta(result)
                }else{
                    Toast.makeText(this@ActualizarEstatusActivity, "Error: $e", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun serializarRespuesta(json: String){
        try {
            val gson : Gson = Gson()
            val respuestaEstatus= gson.fromJson(json, Respuesta::class.java)
            if (!respuestaEstatus.error){
                Toast.makeText(this@ActualizarEstatusActivity, "El estatus ha sido actualizado correctamente.", Toast.LENGTH_LONG).show()
                val intentRegreso = Intent()
                intentRegreso.putExtra("estatusEnvioActualizado", gson.toJson(envio))
                setResult(RESULT_OK, intentRegreso)
                finish()
            }else{
                Log.e("Error", respuestaEstatus.mensaje)
                Toast.makeText(this@ActualizarEstatusActivity, respuestaEstatus.mensaje,Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception){
            Toast.makeText(this@ActualizarEstatusActivity, "Lo sentimos hubo un error en la solicitud",Toast.LENGTH_LONG).show()
        }
    }
}