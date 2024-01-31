package com.example.sheduller.presentation.CalendarAction

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduller.R
import com.example.sheduller.databinding.ActivityItemCalendarBinding
import com.example.sheduller.databinding.CalendarDayItemBinding
import com.example.sheduller.databinding.CalendarMonthItemBinding
import java.util.zip.Inflater

class MonthAdapter(val context: Context, val monthes: ArrayList<MonthModel>,
                   private var viewEventsDay: (Int, Int, String) -> Unit,
    private var viewSticker: (DayModel, TextView) -> Unit
): RecyclerView.Adapter<MonthHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthHolder {

        val binding: CalendarMonthItemBinding = CalendarMonthItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MonthHolder(binding)
    }

    override fun getItemCount(): Int {
        return monthes.size
    }

    override fun onBindViewHolder(holder: MonthHolder, position: Int) {

        holder.binding.month.text = monthes[position].nameMonth


        loadDays(holder.binding.recyclerDays,monthes[position].days)

    }



    private fun loadDays(recyclerView: RecyclerView,days: ArrayList<DayModel>) {



        val daysAdapter = DayAdapter(days,  { model: DayModel -> actionsDay(model) },
             { model: DayModel, sticker: TextView-> setSticker(model, sticker) }
        )

        recyclerView.adapter = daysAdapter
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 7)
        recyclerView.layoutManager = mLayoutManager

    }

    private fun actionsDay(model:DayModel) {

        viewEventsDay(model.month, model.year, model.day)


    }

    private fun setSticker(model:DayModel,sticker:TextView) {

        viewSticker(model, sticker)

    }




}

class MonthHolder(val binding: CalendarMonthItemBinding): RecyclerView.ViewHolder(binding.root){



}

