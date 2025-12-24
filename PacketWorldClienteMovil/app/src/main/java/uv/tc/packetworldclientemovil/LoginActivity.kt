package uv.tc.packetworldclientemovil

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import uv.tc.packetworldclientemovil.databinding.ActivityLoginBinding
import uv.tc.packetworldclientemovil.dto.RSAutenticacionConductor
import uv.tc.packetworldclientemovil.utilidades.Constantes
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        cargarUsuario()
        enableEdgeToEdge()
        binding.root.ajustarAInsets()
        window.statusBarColor = ContextCompat.getColor(this, R.color.rojoOscuro)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.azulOscuro)
        val view = binding.root
        setContentView(view)

        binding.btnIngresar.setOnClickListener {
            if (sonCamposValidos()) {
                deshabilitarCampos()
                consumirAPI(binding.etNoPersonal.text.toString(), binding.etContraseA.text.toString())
            }else{
                Toast.makeText(this, "Campos faltante, favor de completarlos", Toast.LENGTH_SHORT).show()
            }

        }

        binding.etNoPersonal.addTextChangedListener { text ->
            if (text.toString().isNotEmpty()) {
                binding.tilNoPersonal.error = null
            }
        }
        binding.etContraseA.addTextChangedListener { text ->
            if (text.toString().isNotEmpty()) {
                binding.tilContraseA.error = null
            }
        }
    }

    private fun sonCamposValidos(): Boolean{
        var camposValidos = true
        if (binding.etNoPersonal.text.toString().isEmpty()){
            binding.tilNoPersonal.error = "Obligatorio"
            camposValidos = false
        }
        if (binding.etContraseA.text.toString().isEmpty()){
            binding.tilContraseA.error = "Obligatorio"
            camposValidos = false
        }
        return camposValidos
    }

    fun consumirAPI(noPersonal: String?, contraseña: String?){
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)
        Ion.with(this@LoginActivity)
            .load(Constantes().PETICION_POST, "${Constantes().URL_API}autenticacion/conductor")
            .setHeader(Constantes().HEADER_CONTENT_TYPE, Constantes().TIPO_FORM)
            .setBodyParameter("noPersonal", noPersonal)
            .setBodyParameter("contraseña", contraseña)
            .asString(Charsets.UTF_8)
            .setCallback { e, result ->
                if (e == null) {
                    serealizarRespuesta(result)
                }else{
                    habilitarCampos()
                    Log.e("Error", "Inicio sesion: "+e.toString())
                    Toast.makeText(this@LoginActivity, "Error: $e", Toast.LENGTH_LONG).show()
                }
            }

    }

    private fun serealizarRespuesta(json: String){
        try {
            val gson = Gson()
            val respuestaLogin = gson.fromJson(json, RSAutenticacionConductor::class.java)
            if (!respuestaLogin.error){
                if (binding.cbMantenerSesion.isChecked){
                    guardarSesion(binding.etNoPersonal.text.toString(), binding.etContraseA.text.toString(), true)
                }else{
                    guardarSesion("", "", false)
                }
                if (respuestaLogin.conductor?.apellidoMaterno==null){
                    respuestaLogin.conductor?.apellidoMaterno=""
                }
                Toast.makeText(this@LoginActivity, "Bienvenido conductor ${respuestaLogin.conductor?.noPersonal},\n${respuestaLogin.conductor?.nombre} ${respuestaLogin.conductor?.apellidoPaterno} ${respuestaLogin.conductor?.apellidoMaterno}", Toast.LENGTH_LONG).show()
                val intent= Intent(this, EnviosActivity::class.java)
                intent.putExtra("conductor", json)
                startActivity(intent)
                finish()
            }else{
                habilitarCampos()
                Toast.makeText(this@LoginActivity, "Usuario no valido, favor de verificar las credenciales", Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception) {
            habilitarCampos()
            Toast.makeText(
                this@LoginActivity,
                "Lo sentimos, hubo un error en la solicitud",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun guardarSesion(noPersonal: String?, contraseña: String?, guardado: Boolean){
        val editor = getSharedPreferences("Sesion",Context.MODE_PRIVATE)
        editor.edit{
            putString("noPersonal", noPersonal)
            putString("contraseña", contraseña)
            putBoolean("guardado", guardado)
        }
    }

    private fun cargarUsuario(){
        val preferencias = getSharedPreferences("Sesion",Context.MODE_PRIVATE)
        if (preferencias.getBoolean("guardado", false)){
            binding.etNoPersonal.setText(preferencias.getString("noPersonal",""))
            binding.etContraseA.setText(preferencias.getString("contraseña",""))
            binding.cbMantenerSesion.isChecked = true
            deshabilitarCampos()
            consumirAPI(preferencias.getString("noPersonal",""),preferencias.getString("contraseña",""))
        }else{
            binding.cbMantenerSesion.isChecked = false
        }
    }

    private fun deshabilitarCampos(){
        binding.tilNoPersonal.isEnabled = false
        binding.tilContraseA.isEnabled = false
        binding.cbMantenerSesion.isEnabled = false
        binding.btnIngresar.isEnabled = false
    }

    private fun habilitarCampos(){
        binding.tilNoPersonal.isEnabled = true
        binding.tilContraseA.isEnabled = true
        binding.cbMantenerSesion.isEnabled = true
        binding.btnIngresar.isEnabled = true
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

