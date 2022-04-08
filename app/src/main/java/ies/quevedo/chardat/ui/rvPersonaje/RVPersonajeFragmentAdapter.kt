package ies.quevedo.chardat.ui.rvPersonaje

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import ies.quevedo.chardat.placeholder.PlaceholderContent.PlaceholderItem
import ies.quevedo.chardat.databinding.CardPersonajeFragmentBinding

class RVPersonajeFragmentAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<RVPersonajeFragmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardPersonajeFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: CardPersonajeFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}