package uv.tc.packetworldclientemovil

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import uv.tc.packetworldclientemovil.databinding.ActivityActualizarPerfilBinding
import uv.tc.packetworldclientemovil.databinding.ActivityPerfilBinding
import uv.tc.packetworldclientemovil.databinding.FragmentBottomSheetConfirmacionBinding
import uv.tc.packetworldclientemovil.dto.Respuesta
import uv.tc.packetworldclientemovil.poko.Conductor
import uv.tc.packetworldclientemovil.utilidades.Constantes
import uv.tc.packetworldclientemovil.utilidades.DialogoCarga
import uv.tc.packetworldclientemovil.utilidades.ajustarAInsets
import uv.tc.packetworldclientemovil.utilidades.descargarFoto
import androidx.core.content.edit
import java.io.ByteArrayOutputStream
import kotlin.math.min
import androidx.core.graphics.scale
import de.hdodenhof.circleimageview.CircleImageView

class ActualizarPerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarPerfilBinding

    private lateinit var conductor: Conductor
    private val gson = Gson()
    private var fotoBytes: ByteArray? = null
    private val loading = DialogoCarga(this)

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

        descargarFoto(conductor.idColaborador, this@ActualizarPerfilActivity, binding.imgvPerfil)
        binding.etNombre.setText(conductor.nombre)
        binding.etApellidoPaterno.setText(conductor.apellidoPaterno)
        binding.etApellidoMaterno.setText(conductor.apellidoMaterno)
        binding.etCurp.setText(conductor.CURP)
        binding.etCorreo.setText(conductor.correo)

        binding.btnGuardar.setOnClickListener {
            if (sonCamposValidos()){
                desabilitarCampos()
                loading.startLoading("Actualizando datos ...")
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

        binding.btnSeleccionarImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            seleccionarFoto.launch(intent)
        }

    }

    private val seleccionarFoto = this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == RESULT_OK){
            val imgUri = result.data?.data
            if (imgUri != null){
                fotoBytes = procesarImagen(this, imgUri, binding.imgvPerfil)
                val bottomSheetConfirmacion = BottomSheetConfirmacionFragment()
                bottomSheetConfirmacion.onAceptarListener = {
                    subirFoto()
                }
                bottomSheetConfirmacion.onNuevamenteListener = {
                    descargarFoto(conductor.idColaborador, this@ActualizarPerfilActivity, binding.imgvPerfil)
                    binding.btnSeleccionarImagen.performClick()
                }
                bottomSheetConfirmacion.onCancelarListener = {
                    descargarFoto(conductor.idColaborador, this@ActualizarPerfilActivity, binding.imgvPerfil)
                }
                bottomSheetConfirmacion.show(supportFragmentManager, BottomSheetConfirmacionFragment.TAG)
            }
        }
    }

    private fun procesarImagen(context: Context, uri: Uri, imgView : CircleImageView): ByteArray? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmapOriginal = BitmapFactory.decodeStream(inputStream) ?: return null
            inputStream?.close()

            val ancho = bitmapOriginal.width
            val alto = bitmapOriginal.height
            val lado = min(ancho, alto)

            val x = (ancho - lado) / 2
            val y = (alto - lado) / 2

            val bitmapCuadrado = Bitmap.createBitmap(bitmapOriginal, x, y, lado, lado)
            val bitmapFinal = bitmapCuadrado.scale(500, 500)

            val stream = ByteArrayOutputStream()
            bitmapFinal.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()

            if (bitmapOriginal != bitmapFinal) bitmapOriginal.recycle()
            if (bitmapCuadrado != bitmapFinal) bitmapCuadrado.recycle()

            imgView.setImageBitmap(bitmapFinal)

            return byteArray

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun subirFoto(){
        Ion.with(this@ActualizarPerfilActivity)
            .load(Constantes().PETICION_PUT, "${Constantes().URL_API}colaborador/subir-foto/${conductor.idColaborador}")
            .setByteArrayBody(fotoBytes)
            .asString(Charsets.UTF_8)
            .setCallback { e, result ->
                if (e == null) {
                    serealizarRespuestaFoto(result)
                }else{
                    Toast.makeText(this@ActualizarPerfilActivity, "Error: $e", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun serealizarRespuestaFoto(json: String){
        try {
            val respuesta = gson.fromJson(json, Respuesta::class.java)
            if (!respuesta.error){
                Toast.makeText(this@ActualizarPerfilActivity, "Foto actualizada correctamente", Toast.LENGTH_LONG).show()
            }
        }catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(this@ActualizarPerfilActivity, "Error: $e", Toast.LENGTH_LONG).show()
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
                        prefs.edit { putString("contraseña", binding.etContraseA.text.toString()) }
                    }
                    prefConductor.edit { putString("conductor", jsonConductor) }
                    serealizarRespuesta(result)
                }else{
                    habilitarCampos()
                    loading.stopLoading()
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
                loading.stopLoading()
                Toast.makeText(this@ActualizarPerfilActivity, "Error: ${respuestaActualizarPerfil.mensaje}", Toast.LENGTH_LONG).show()
            }
        }catch (e : Exception){
            habilitarCampos()
            loading.stopLoading()
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

    class BottomSheetConfirmacionFragment : BottomSheetDialogFragment() {

        private var _binding: FragmentBottomSheetConfirmacionBinding? = null
        private val binding get() = _binding!!

        var onAceptarListener: (() -> Unit)? = null
        var onNuevamenteListener: (() -> Unit)? = null
        var onCancelarListener: (() -> Unit)? = null


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentBottomSheetConfirmacionBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val dialog = dialog as BottomSheetDialog

            dialog.let {
                it.behavior.state = BottomSheetBehavior.STATE_EXPANDED
                it.setCanceledOnTouchOutside(false)
                it.behavior.isDraggable = false
                it.behavior.isHideable = false
                it.window?.setDimAmount(0.0f)
                val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.azulOscuro))
            }

            binding.btnAceptar.setOnClickListener {
                onAceptarListener?.invoke()
                dismiss()
            }

            binding.btnSeleccionar.setOnClickListener {
                onNuevamenteListener?.invoke()
                dismiss()
            }

            binding.btnCancelar.setOnClickListener {
                onCancelarListener?.invoke()
                dismiss()
            }

        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        companion object {
            const val TAG = "BottomSheetConfirmacionFragment"
        }

    }
}