package com.example.sheduller.presentation.EventsDay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.databinding.EventItemBinding
import com.squareup.picasso.Picasso


class EventsAdapter(
) : RecyclerView.Adapter<EventsAdapter.EventsHolder>() {

    private val eventsList = ArrayList<EventModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {

        val binding : EventItemBinding =EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsHolder(binding)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        holder.bind(eventsList[position])

    }

    fun setList(events: List<EventModel>) {
        eventsList.clear()
        eventsList.addAll(events)

    }




    class EventsHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: EventModel

        ) {

            binding.timeStart.text = model.timeStart
            binding.timeEnd.text = model.timeEnd
            binding.eventDescription.text = model.description



        }


    }



}