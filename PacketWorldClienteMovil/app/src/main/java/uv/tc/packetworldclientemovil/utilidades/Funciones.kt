package uv.tc.packetworldclientemovil.utilidades

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import de.hdodenhof.circleimageview.CircleImageView
import uv.tc.packetworldclientemovil.poko.Conductor

fun View.ajustarAInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}

fun descargarFoto(idColaborador: Int, activity : AppCompatActivity, imgView : CircleImageView){
    Ion.with(activity)
        .load(Constantes().PETICION_GET, "${Constantes().URL_API}colaborador/obtener-foto/${idColaborador}")
        .asString(Charsets.UTF_8)
        .setCallback { e, result ->
            if (e == null) {
                serealizarRespuestaFoto(result, activity, imgView)
            } else {
                Toast.makeText(activity, "Error: $e", Toast.LENGTH_LONG).show()
            }
        }
}

private fun serealizarRespuestaFoto(json: String, activity : AppCompatActivity, imgView: CircleImageView) {
    try {
        if (json.isNotEmpty()){
            val gson = Gson()
            val conductor = gson.fromJson(json, Conductor::class.java)
            if (conductor.fotoBase64 != null) {
                val imgBytes = Base64.decode(conductor.fotoBase64, Base64.DEFAULT)
                val imgBitMap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.size)
                imgView.setImageBitmap(imgBitMap)
            }
        }
    }catch (e : Exception){
        Toast.makeText(activity, "Error: $e", Toast.LENGTH_LONG).show()
    }
}