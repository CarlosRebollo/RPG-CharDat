package ies.quevedo.chardat.ui.rvPersonaje

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.CardPersonajeBinding
import ies.quevedo.chardat.domain.Personaje

class RVPersonajeAdapter(
    private val goMainMenu: (Int) -> Unit,
) : ListAdapter<Personaje,
        RVPersonajeAdapter.ItemViewHolder>(PersonajeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_personaje, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            val item = getItem(position)
            bind(item, goMainMenu)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardPersonajeBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun bind(
            item: Personaje,
            goMainMenu: (Int) -> Unit
        ) = with(binding) {
            tvName.text = item.name
            tvClase.text = item.clase
            tvCreationDate.text = "Fecha de creación: " + item.creationDate.toString()
            cardPersonaje.setOnClickListener {
                goMainMenu(adapterPosition)
            }
        }
    }

    class PersonajeDiffCallback : DiffUtil.ItemCallback<Personaje>() {
        override fun areItemsTheSame(oldItem: Personaje, newItem: Personaje): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Personaje, newItem: Personaje): Boolean {
            return oldItem == newItem
        }
    }
}