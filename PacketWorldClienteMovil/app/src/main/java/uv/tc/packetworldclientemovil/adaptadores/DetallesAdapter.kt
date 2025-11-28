package uv.tc.packetworldclientemovil.adaptadores

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uv.tc.packetworldclientemovil.ClienteFragment
import uv.tc.packetworldclientemovil.DestinatarioFragment
import uv.tc.packetworldclientemovil.EnvioFragment
import uv.tc.packetworldclientemovil.PaquetesFragment
import uv.tc.packetworldclientemovil.SucursalFragment

class DetallesAdapter (activity: AppCompatActivity): FragmentStateAdapter(activity){
    // 1. Dinos cuántas pestañas quieres
    override fun getItemCount(): Int = 5

    // 2. Devuelve el Fragment correspondiente a cada posición (0 a 4)
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EnvioFragment()
            1 -> SucursalFragment()
            2 -> DestinatarioFragment()
            3 -> PaquetesFragment()
            4 -> ClienteFragment()
            else -> EnvioFragment()
        }
    }
}