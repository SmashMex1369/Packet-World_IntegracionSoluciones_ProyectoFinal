package uv.tc.packetworldclientemovil.utilidades

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import uv.tc.packetworldclientemovil.LoginActivity
import uv.tc.packetworldclientemovil.R

class DialogoCarga (val activity: Activity) {
    private var dialog: AlertDialog? = null

    fun startLoading(mensaje: String = "Cargando...") {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater

        val vista = inflater.inflate(R.layout.dialog_carga, null)

        val tvMensaje = vista.findViewById<TextView>(R.id.tvMensaje)
        tvMensaje.text = mensaje

        builder.setView(vista)
        builder.setCancelable(false)

        dialog = builder.create()

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog?.show()
    }

    fun stopLoading() {
        dialog?.dismiss()
    }
}