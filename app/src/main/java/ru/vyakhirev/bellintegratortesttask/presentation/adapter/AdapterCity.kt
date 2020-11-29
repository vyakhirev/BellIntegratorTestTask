package ru.vyakhirev.bellintegratortesttask.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vyakhirev.bellintegratortesttask.R
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature

class AdapterCity(
    private val context: Context,
    private var citiesTemperature: List<CityTemperature>,
    val cityClick: ((city: CityTemperature) -> Unit)?,

    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.city_item, parent, false)
        )
    }

    override fun getItemCount(): Int = citiesTemperature.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is CityItemViewHolder)
            holder.bind(citiesTemperature[position])

        if (holder is CityItemViewHolder)
            holder.itemView.setOnClickListener {
                cityClick?.invoke(citiesTemperature[position])
            }

    }

    fun update(data: List<CityTemperature>) {
        citiesTemperature = data
        notifyDataSetChanged()
    }
}