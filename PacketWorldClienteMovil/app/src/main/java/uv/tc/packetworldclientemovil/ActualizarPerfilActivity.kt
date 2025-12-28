package uv.tc.packetworldclientemovil

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarPerfilBinding
import uv.tc.packetworldclientemovil.databinding.ActivityPerfilBinding
import uv.tc.packetworldclientemovil.dto.Respuesta
import uv.tc.packetworldclientemovil.poko.Conductor
import uv.tc.packetworldclientemovil.utilidades.Constantes
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets

class ActualizarPerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarPerfilBinding

    private lateinit var conductor: Conductor
    val gson = Gson()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        binding.root.ajustarAInsets()

        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)

        val perfilConductor : Conductor = gson.fromJson(
            intent.getStringExtra("conductor"), Conductor::class.java)
        conductor = perfilConductor

        binding.etNombre.setText(conductor.nombre)
        binding.etApellidoPaterno.setText(conductor.apellidoPaterno)
        binding.etApellidoMaterno.setText(conductor.apellidoMaterno)
        binding.etCurp.setText(conductor.CURP)
        binding.etCorreo.setText(conductor.correo)

        binding.btnGuardar.setOnClickListener {
            if (sonCamposValidos()){
                desabilitarCampos()
                conductor.nombre = binding.etNombre.text.toString()
                conductor.apellidoPaterno = binding.etApellidoPaterno.text.toString()
                conductor.apellidoMaterno = binding.etApellidoMaterno.text.toString()
                conductor.CURP = binding.etCurp.text.toString()
                conductor.correo = binding.etCorreo.text.toString()
                if (binding.etContraseA.text.toString().isEmpty()){
                    conductor.contraseña = null
                }else{
                    conductor.contraseña = binding.etContraseA.text.toString()
                }
                consumirAPI(conductor)
            }else{
                Toast.makeText(this, "Campos faltante, favor de completarlos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sonCamposValidos(): Boolean{
        var camposValidos = true
        if (binding.etNombre.text.toString().isEmpty()){
            binding.tilNombre.error = "Obligatorio"
            camposValidos = false
        }
        if (binding.etApellidoPaterno.text.toString().isEmpty()){
            binding.tilApellidoPaterno.error = "Obligatorio"
            camposValidos = false
        }
        if (binding.etCurp.text.toString().isEmpty()){
            binding.tilCurp.error = "Obligatorio"
            camposValidos = false
        }
        if (binding.etCorreo.text.toString().isEmpty()){
            binding.tilCorreo.error = "Obligatorio"
            camposValidos = false
        }
        return camposValidos
    }

    private fun consumirAPI(conductor: Conductor){
        val jsonConductor = gson.toJson(conductor)
        Ion.with(this@ActualizarPerfilActivity)
            .load(Constantes().PETICION_PUT,"${Constantes().URL_API}colaborador/actualizar-colaborador")
            .setHeader(Constantes().HEADER_CONTENT_TYPE, Constantes().TIPO_JSON)
            .setStringBody(jsonConductor)
            .asString(Charsets.UTF_8)
            .setCallback { e, result ->
                if (e == null) {
                    val prefs = getSharedPreferences("Sesion", MODE_PRIVATE)
                    val prefConductor = getSharedPreferences("DatosConductor", MODE_PRIVATE)
                    if (binding.etContraseA.text.toString().isNotEmpty()){
                        prefs.edit().putString("contraseña", binding.etContraseA.text.toString()).apply()
                    }
                    prefConductor.edit().putString("conductor", jsonConductor).apply()
                    serealizarRespuesta(result)

                }else{
                    habilitarCampos()
                    Toast.makeText(this@ActualizarPerfilActivity, "Error: $e", Toast.LENGTH_LONG).show()
                }

            }
    }

    private fun serealizarRespuesta(json: String){
        try {
            val respuestaActualizarPerfil = gson.fromJson(json, Respuesta::class.java)
            if (!respuestaActualizarPerfil.error){
                Toast.makeText(this@ActualizarPerfilActivity, "Perfil actualizado correctamente", Toast.LENGTH_LONG).show()
                finish()
            }else{
                habilitarCampos()
                Toast.makeText(this@ActualizarPerfilActivity, "Error: ${respuestaActualizarPerfil.mensaje}", Toast.LENGTH_LONG).show()
            }
        }catch (e : Exception){
            habilitarCampos()
            Toast.makeText(this@ActualizarPerfilActivity, "Error: $e", Toast.LENGTH_LONG).show()
        }
    }

    private fun desabilitarCampos(){
        binding.tilNombre.isEnabled = false
        binding.tilApellidoPaterno.isEnabled = false
        binding.tilApellidoMaterno.isEnabled = false
        binding.tilCurp.isEnabled = false
        binding.tilCorreo.isEnabled = false
        binding.tilContraseA.isEnabled = false
        binding.btnGuardar.isEnabled = false
    }

    private fun habilitarCampos(){
        binding.tilNombre.isEnabled = true
        binding.tilApellidoPaterno.isEnabled = true
        binding.tilApellidoMaterno.isEnabled = true
        binding.tilCurp.isEnabled = true
        binding.tilCorreo.isEnabled = true
        binding.tilContraseA.isEnabled = true
        binding.btnGuardar.isEnabled = true
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}