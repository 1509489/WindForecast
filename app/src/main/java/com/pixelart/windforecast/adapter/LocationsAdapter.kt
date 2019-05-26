package com.pixelart.windforecast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pixelart.windforecast.R
import com.pixelart.windforecast.data.entities.LocationEntity

class LocationsAdapter(private val listener: OnItemClickedListener):
    ListAdapter<LocationEntity, LocationsAdapter.ViewHolder>(DIFF_CALLBACK) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_adapter_layout, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = getItem(position)
        holder.apply {
            setContent(location)
            itemView.setOnClickListener { listener.onItemClicked(position) }
        }
    }
    
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val locationName: TextView = view.findViewById(R.id.tvLocationName)
        private val population: TextView = view.findViewById(R.id.tvPopulation)
        
        fun setContent(location: LocationEntity){
            val name = "${location.name}, ${location.countryCode}"
            locationName.text = name
            val populationText = "${itemView.context.resources.getString(R.string.population)} ${location.population}"
            population.text = populationText
        }
    }
    
    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<LocationEntity> =
            object : DiffUtil.ItemCallback<LocationEntity>(){
                override fun areItemsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
                    return oldItem.id == newItem.id
                }
    
                override fun areContentsTheSame(oldItem: LocationEntity, newItem: LocationEntity): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
    
    interface OnItemClickedListener{
        fun onItemClicked(position: Int)
    }
}