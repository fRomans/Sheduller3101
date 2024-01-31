package com.example.sheduller

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.databinding.CalendarItemLayoutBinding
import java.util.*

class CalendarAdapter(
    val listener: Listener,
    var calendars: ArrayList<CalendarModel>
) : RecyclerView.Adapter<CalendarHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CalendarItemLayoutBinding =
            CalendarItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CalendarHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        holder.bind(calendars[position], listener)
    }

    override fun getItemCount(): Int {
        return calendars.size
    }

    interface Listener {
        fun onClickItem(calendarmodel: CalendarModel)
    }
}


class CalendarHolder(var binding: CalendarItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(calendarModel: CalendarModel, listener: CalendarAdapter.Listener) {

        binding.userName.text = calendarModel.calendarName

        itemView.setOnClickListener {
            listener.onClickItem(calendarModel)

        }

    }

}