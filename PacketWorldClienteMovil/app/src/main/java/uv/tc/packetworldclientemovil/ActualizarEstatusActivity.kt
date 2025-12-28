package uv.tc.packetworldclientemovil

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarEstatusBinding
import uv.tc.packetworldclientemovil.poko.EstatusEnvio
import uv.tc.packetworldclientemovil.utilidades.Constantes
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets
import kotlin.collections.get

class ActualizarEstatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarEstatusBinding
    private lateinit var estatusEnvio: EstatusEnvio
    private lateinit var listaEstatusFiltrada : List<EstatusEnvio>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarEstatusBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)
        //binding.spnEstatus.setAdapter(ArrayAdapter(this,R.layout.item_custom_spinner,resources.getStringArray(R.array.spn_estatus)))

    }

    override fun onStart() {
        super.onStart()
        cargarEstatusEnvioAPI()
        binding.spnEstatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val estatus = listaEstatusFiltrada[position]
                if (estatus.idEstatusEnvio==4||estatus.idEstatusEnvio==6){
                    binding.tilMotivo.hint = "Obligatorio"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

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
            .load("GET", "${Constantes().URL_API}catalogo/obtener-estatus-envio")
            .asString(Charsets.UTF_8)
            .setCallback { e, result ->
                if(e==null){
                    cargarEstatusEnvioComboBox(result)
                }else{
                    Toast.makeText(this@ActualizarEstatusActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
    }


}