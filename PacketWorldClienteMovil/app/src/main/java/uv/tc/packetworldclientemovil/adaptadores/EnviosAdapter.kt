package uv.tc.packetworldclientemovil.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uv.tc.packetworldclientemovil.R
import uv.tc.packetworldclientemovil.databinding.ItemRecyclerEnvioBinding
import uv.tc.packetworldclientemovil.poko.Envio

class EnviosAdapter (
    private val envios: List<Envio>,
    private val onItemClicked: (Envio) -> Unit
) : RecyclerView.Adapter<EnviosAdapter.EnviosViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnviosViewHolder {
        val binding = ItemRecyclerEnvioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EnviosViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EnviosViewHolder,
        position: Int
    ) {
        val envio = envios[position]
        holder.bind(envio)
        holder.itemView.setOnClickListener {
            //onItemClicked(envio, position)
        }
    }

    fun actualizarItem(position: Int, envioActualizado : Envio){
        envios[position]
    }

    override fun getItemCount(): Int {
        return envios.size
    }


    class EnviosViewHolder(private val binding: ItemRecyclerEnvioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(envio: Envio) {
            binding.tvNoGuiaValor.text = envio.noGuia
            val direccion = "${envio.estadoDest}, ${envio.ciudadDest}\nC.P. ${envio.codigoPostalDest} Col. ${envio.coloniaDest}\nCalle ${envio.calleDest} #${envio.numDest}"
            binding.tvDireccionValor.text = direccion
            binding.tvEstatusValor.text = envio.estatus
        }
    }


}