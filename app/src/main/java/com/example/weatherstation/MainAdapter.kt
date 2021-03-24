package com.example.weatherstation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherstation.data.Records

class MainAdapter(private val onClickListener: OnClickListener
) :
    ListAdapter<Records, MainAdapter.WeatherViewHolder>(DiffCallback) {

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemSit: TextView = itemView.findViewById(R.id.item_sit)
        private val itemAQI: TextView = itemView.findViewById(R.id.item_aqi)
        private val itemStatus: TextView = itemView.findViewById(R.id.item_status)
        private val itemPollutant: TextView = itemView.findViewById(R.id.item_pollutant)
        private val itemWindSpeed: TextView = itemView.findViewById(R.id.item_wind_apeed)
        private val itemDate: TextView = itemView.findViewById(R.id.item_date)
        private val item038: TextView = itemView.findViewById(R.id.item_038)

        fun bind(records: Records) {
            itemSit.text = records.siteId
            itemAQI.text = records.aqi
            itemStatus.text = records.status
            itemPollutant.text = records.pollutant
            itemWindSpeed.text = records.windSpeed
            itemDate.text = records.importDate
            item038.text = records.o38hr
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Records>() {
        override fun areItemsTheSame(
            oldItem: Records,
            newItem: Records
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Records,
            newItem: Records
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        //載入項目模板
        val inflater = LayoutInflater.from(parent.context)
        val example = inflater.inflate(R.layout.item_weather_station, parent, false)
        return WeatherViewHolder(example)

    }
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val records = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(records)
        }
        holder.bind(records)
    }

    class OnClickListener(val clickListener: (records: Records) -> Unit) {
        fun onClick(records: Records) = clickListener(records)
    }
}