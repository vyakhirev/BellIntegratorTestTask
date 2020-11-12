package ru.vyakhirev.bellintegratortesttask.presentation.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_item.view.*
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature

class CityItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: CityTemperature) {

        itemView.cityNameTV.setTextColor(Color.parseColor("#0972C5"))
        itemView.cityNameTV.text = item.city

        itemView.temperatureTV.setTextColor(Color.parseColor("#0972C5"))
        itemView.temperatureTV.text = item.temperature.toString()
    }

}