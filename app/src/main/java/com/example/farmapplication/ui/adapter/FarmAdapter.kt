package com.example.farmapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.farmapplication.R
import com.example.farmapplication.data.model.FarmEntity
import com.example.farmapplication.ui.home.HomeFragmentDirections

class FarmAdapter(private val context: Context, private val itemClick: (FarmEntity) -> Unit) :
    ListAdapter<FarmEntity, RecyclerView.ViewHolder>(FarmDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_farm, parent, false)
        return FarmHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val farm = getItem(position)
        (holder as FarmHolder).bindItem(farm)
    }

    class FarmHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val imgFarm: ImageView = itemView.findViewById(R.id.farm_item_image)
        private val tvTitle: TextView = itemView.findViewById(R.id.farm_item_title)

        fun bindItem(farmEntity: FarmEntity) {
            imgFarm.load(farmEntity.imagePath)
            tvTitle.text = farmEntity.name
            itemView.setOnClickListener {
                navigateToPlant(farmEntity, itemView)
            }
        }

        private fun navigateToPlant(farmEntity: FarmEntity, view: View) {
            val direction = HomeFragmentDirections
                .actionHomeFragmentToDetailFragment(farmEntity)
            view.findNavController().navigate(direction)
        }
    }
}

private class FarmDiffCallback : DiffUtil.ItemCallback<FarmEntity>() {

    override fun areItemsTheSame(oldItem: FarmEntity, newItem: FarmEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FarmEntity, newItem: FarmEntity): Boolean {
        return oldItem == newItem
    }
}