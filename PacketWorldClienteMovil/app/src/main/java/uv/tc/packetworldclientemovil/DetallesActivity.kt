package uv.tc.packetworldclientemovil

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uv.tc.packetworldclientemovil.adaptadores.DetallesAdapter
import uv.tc.packetworldclientemovil.databinding.ActivityDetallesBinding

class DetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 1. Asignamos el adaptador
        val adapter = DetallesAdapter(this)
        binding.vpContenido.adapter = adapter

        // 2. Conectamos Tabs con ViewPager usando TabLayoutMediator
        TabLayoutMediator(binding.tlSecciones, binding.vpContenido){tab, position ->
            val customView = layoutInflater.inflate(R.layout.item_tab_personalizado,null) as TextView

            when(position){
                // Aquí configuras el título o icono de cada pestaña según su posición
                0 -> {
                    customView.text= ContextCompat.getString(this, R.string.icono_envio)
                    //tab.setIcon(R.drawable.ic_home)
                }
                1 -> {
                    customView.text= ContextCompat.getString(this, R.string.icono_sucursal)
                }
                2 -> {
                    customView.text= ContextCompat.getString(this, R.string.icono_destinatario)
                }
                3 -> {
                    customView.text= ContextCompat.getString(this, R.string.icono_paquete)
                }
                4 -> {
                    customView.text= ContextCompat.getString(this, R.string.icono_contacto)
                }
            }
            tab.customView = customView
        }.attach() // ¡Importante llamar a attach() al final!
    }
}