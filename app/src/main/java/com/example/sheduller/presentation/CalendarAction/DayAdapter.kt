package com.example.sheduller.presentation.CalendarAction

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.databinding.CalendarDayItemBinding

 class DayAdapter(var days: ArrayList<DayModel>,
                 private var actionsDay: (DayModel) -> Unit,
    private var setSticker: (DayModel, TextView) -> Unit
):RecyclerView.Adapter<DayHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {

        val binding : CalendarDayItemBinding = CalendarDayItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DayHolder(binding)
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        holder.bind(days[position], actionsDay, setSticker)

    }

    override fun getItemCount(): Int {
        return days.size
    }
}

class DayHolder(val binding: CalendarDayItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(model: DayModel, actionsDay: (DayModel) -> Unit,setSticker: (DayModel, TextView) -> Unit){


        binding.day.text = model.day


        if (model.day.toInt() == 0) {
            binding.day.setTextColor(Color.TRANSPARENT)
        }


        setSticker(model, binding.day)



        binding.sectionDay.setOnClickListener(View.OnClickListener {
            actionsDay(model)


        })
    }


}