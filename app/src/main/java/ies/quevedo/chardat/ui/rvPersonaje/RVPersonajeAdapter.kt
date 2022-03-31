package ies.quevedo.chardat.ui.rvPersonaje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.CardPersonajeBinding
import ies.quevedo.chardat.domain.Personaje
import ies.quevedo.chardat.domain.enums.Clase

class RVPersonajeAdapter : ListAdapter<Personaje, RVPersonajeAdapter.ItemViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_personaje, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            val item = getItem(position)
            bind(item)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardPersonajeBinding.bind(itemView)
        fun bind(personaje: Personaje) = with(binding) {
            tvName.text = personaje.name

            ivClass.load(getClassIcon(personaje.clase))
        }

        private fun getClassIcon(clase: Clase): String? {
            var classIcon: String? = null
            when (clase.toString()) {
                "ARTIFICIER" -> classIcon = "classes/Artificer.jpg"
                "BARBARIAN" -> classIcon = "classes/Barbarian.jpg"
                "BARD" -> classIcon = "classes/Bard.jpg"
                "CLERIC" -> classIcon = "classes/Cleric.jpg"
                "DRUID" -> classIcon = "classes/Druid.jpg"
                "FIGHTER" -> classIcon = "classes/Fighter.jpg"
                "MONK" -> classIcon = "classes/Monk.jpg"
                "PALADIN" -> classIcon = "classes/Paladin.jpg"
                "RANGER" -> classIcon = "classes/Ranger.jpg"
                "ROGUE" -> classIcon = "classes/Rogue.jpg"
                "SORCERER" -> classIcon = "classes/Sorcerer.jpg"
                "WARLOCK" -> classIcon = "classes/Warlock.jpg"
                "WIZARD" -> classIcon = "classes/Wizard.jpg"
            }
            return classIcon
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Personaje>() {

        override fun areItemsTheSame(oldItem: Personaje, newItem: Personaje): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Personaje, newItem: Personaje): Boolean {
            return oldItem == newItem
        }
    }
}