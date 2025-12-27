package uv.tc.packetworldclientemovil.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uv.tc.packetworldclientemovil.databinding.ItemRecyclerDescripcionPaqueteBinding
import uv.tc.packetworldclientemovil.poko.Paquete

class PaquetesAdapter (
    private val paquetes: List<Paquete>

) : RecyclerView.Adapter<PaquetesAdapter.PaquetesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaquetesAdapter.PaquetesViewHolder {
        val binding = ItemRecyclerDescripcionPaqueteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaquetesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PaquetesAdapter.PaquetesViewHolder,
        position: Int
    ) {
        val paquete = paquetes[position]
        holder.bind(paquete)
    }

    override fun getItemCount(): Int {
        return paquetes.size
    }

    class PaquetesViewHolder(private val binding: ItemRecyclerDescripcionPaqueteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(paquete: Paquete){
            binding.tvDescripcionValor.text = paquete.descripcion
            binding.tvDescripcion.text = "Descripcion del Paquete ${position+1}:"
        }
    }

}