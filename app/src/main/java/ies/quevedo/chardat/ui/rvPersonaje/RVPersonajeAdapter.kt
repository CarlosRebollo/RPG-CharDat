package ies.quevedo.chardat.ui.rvPersonaje

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.CardPersonajeFragmentBinding
import ies.quevedo.chardat.domain.Personaje

class RVPersonajeAdapter(
    private val goMainMenu: (Int) -> Unit
) : ListAdapter<Personaje,
        RVPersonajeAdapter.ItemViewHolder>(PersonajeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_personaje_fragment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            val item = getItem(position)
            bind(item, goMainMenu)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardPersonajeFragmentBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(
            item: Personaje,
            goMainMenu: (Int) -> Unit
        ) = with(binding) {
            setImageClass(item)
            tvName.text = item.name
            tvClase.text = item.clase
            tvCreationDate.text = "Fecha de creación: ${item.creationDate}"
            cardPersonaje.setOnClickListener { goMainMenu(adapterPosition) }
        }

        private fun CardPersonajeFragmentBinding.setImageClass(item: Personaje) {
            when (item.clase) {
                "GUERRERO" -> ivClass.setImageResource(R.drawable.guerrero)
                "GUERRERO ACRÓBATA" -> ivClass.setImageResource(R.drawable.guerrero_acr_bata)
                "PALADÍN" -> ivClass.setImageResource(R.drawable.palad_n)
                "PALADÍN OSCURO" -> ivClass.setImageResource(R.drawable.paladin_oscuro)
                "MAESTRO DE ARMAS" -> ivClass.setImageResource(R.drawable.maestro_de_armas)
                "TECNICISTA" -> ivClass.setImageResource(R.drawable.tecnicista)
                "TAO" -> ivClass.setImageResource(R.drawable.tao)
                "EXPLORADOR" -> ivClass.setImageResource(R.drawable.explorador)
                "SOMBRA" -> ivClass.setImageResource(R.drawable.sombra)
                "LADRÓN" -> ivClass.setImageResource(R.drawable.ladr_n)
                "ASESINO" -> ivClass.setImageResource(R.drawable.asesino)
                "HECHICERO" -> ivClass.setImageResource(R.drawable.hechicero)
                "WARLOCK" -> ivClass.setImageResource(R.drawable.warlock)
                "ILUSIONISTA" -> ivClass.setImageResource(R.drawable.ilusionista)
                "HECHICERO MENTALISTA" -> ivClass.setImageResource(R.drawable.hechicero_mentalista)
                "CONJURADOR" -> ivClass.setImageResource(R.drawable.conjurador)
                "GUERRERO CONJURADOR" -> ivClass.setImageResource(R.drawable.guerrero_conjurador)
                "MENTALISTA" -> ivClass.setImageResource(R.drawable.mentalista)
                "GUERRERO MENTALISTA" -> ivClass.setImageResource(R.drawable.guerrero_mentalista)
                "NOVEL" -> ivClass.setImageResource(R.drawable.novel)
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