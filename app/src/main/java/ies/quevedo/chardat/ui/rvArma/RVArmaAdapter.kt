package ies.quevedo.chardat.ui.rvArma

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.CardArmaFragmentBinding
import ies.quevedo.chardat.domain.Arma

class RVArmaAdapter(
    private val goWeaponDetails: (Int) -> Unit
) : ListAdapter<Arma,
        RVArmaAdapter.ItemViewHolder>(ArmaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_arma_fragment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            val item = getItem(position)
            bind(item, goWeaponDetails)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardArmaFragmentBinding.bind(itemView)
        fun bind(
            item: Arma,
            goWeaponDetails: (Int) -> Unit
        ) = with(binding) {
            tvName.text = item.name
            tvDescription.text = item.description
            cardArma.setOnClickListener { goWeaponDetails(adapterPosition) }
        }
    }

    class ArmaDiffCallback : DiffUtil.ItemCallback<Arma>() {
        override fun areItemsTheSame(oldItem: Arma, newItem: Arma): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Arma, newItem: Arma): Boolean {
            return oldItem == newItem
        }
    }
}